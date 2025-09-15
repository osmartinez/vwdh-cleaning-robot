package domain.events

import java.time.Instant
import java.util.UUID

sealed interface DomainEvent{
    val id: UUID
    val happenedAt: Instant
}