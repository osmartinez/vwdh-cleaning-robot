package domain

import domain.exceptions.DomainException
import java.util.UUID

data class Robot (val position: Position, val orientation: Orientation, val floor: Floor){
    override fun toString(): String {
        return "${position.x} ${position.y} ${orientation.name}"
    }
    fun rotateClockwise(): Robot = copy(orientation= orientation.clockwise())
    fun rotateAnticlockwise(): Robot = copy(orientation= orientation.anticlockwise())
    fun move(): Robot {
        val next = position.forward(orientation)
        if(!floor.contains(next)) throw DomainException("Robot is trying to move outside the floor")
        return copy(position = next)
    }
}

@JvmInline
value class RobotId(val value: UUID) {
    override fun toString(): String = value.toString()
    companion object {
        fun new(): RobotId = RobotId(UUID.randomUUID())
    }
}