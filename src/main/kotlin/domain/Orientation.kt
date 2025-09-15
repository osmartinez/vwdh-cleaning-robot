package domain

enum class Orientation {
    N, E, S, W;

    fun clockwise(): Orientation = when (this) {
        N -> E
        E -> S
        S -> W
        W -> N
    }

    fun anticlockwise(): Orientation = when (this) {
        N -> W
        W -> S
        S -> E
        E -> N
    }
}
