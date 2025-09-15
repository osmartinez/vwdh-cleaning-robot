package domain

import domain.exceptions.DomainException

data class Position (val x: Int, val y: Int) {
    init {
        if (x < 0 || y < 0) {
            throw DomainException("Invalid position: ($x,$y)")
        }
    }

    fun forward(orientation: Orientation) = when (orientation) {
        Orientation.N -> copy(y = y + 1)
        Orientation.E -> copy(x = x + 1)
        Orientation.S -> copy(y = y - 1)
        Orientation.W -> copy(x = x - 1)
    }
}