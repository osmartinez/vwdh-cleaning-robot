package domain.events

import domain.Position
import domain.RobotId
import java.time.Instant
import java.util.UUID

data class RobotMoved(
    override val robotId: RobotId,
    val from: Position,
    val to: Position,
    override val happenedAt: Instant = Instant.now(),
    override val id: UUID = UUID.randomUUID()
    ) : RobotEvent