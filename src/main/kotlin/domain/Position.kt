package domain

data class Position (val x: Int, val y: Int) {

    fun forward(orientation: Orientation) = when (orientation) {
        Orientation.N -> copy(y = y + 1)
        else -> copy(x = x + 1)
    }
}