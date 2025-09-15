package domain

enum class Orientation {
    N, E, S, W;

    fun clockwise(): Orientation = when (this) {
        N -> E
        E -> S
        S -> W
        W -> N
    }
}
