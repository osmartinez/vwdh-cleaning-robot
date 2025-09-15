package domain

import domain.events.DomainEvent
import domain.events.RobotMoved
import domain.exceptions.DomainException
import java.util.UUID

data class Robot(
    val id: RobotId,
    val position: Position,
    val orientation: Orientation,
    val floor: Floor
) {

    private val pendingEvents = mutableListOf<DomainEvent>()

    override fun toString(): String =
        "${position.x} ${position.y} ${orientation.name}"

    fun rotateClockwise(): Robot =
        copy(orientation = orientation.clockwise())

    fun rotateAnticlockwise(): Robot =
        copy(orientation = orientation.anticlockwise())

    fun move(): Robot {
        val prev = position
        val next = position.forward(orientation)
        if (!floor.contains(next)) throw DomainException("Robot is trying to move outside the floor")
        pendingEvents+= RobotMoved(id,prev,next)
        return copy(position = next)
    }

    fun pullDomainEvents(): List<DomainEvent> =
        pendingEvents.toList().also { pendingEvents.clear() }

    companion object {
        fun create(position: Position, orientation: Orientation, floor: Floor): Robot =
            Robot(RobotId.new(), position, orientation, floor)
    }
}

@JvmInline
value class RobotId(val value: UUID) {
    override fun toString(): String = value.toString()
    companion object {
        fun new(): RobotId = RobotId(UUID.randomUUID())
    }
}