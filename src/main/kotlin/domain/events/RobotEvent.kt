package domain.events

import domain.RobotId

sealed interface RobotEvent : DomainEvent {
    val robotId: RobotId
}