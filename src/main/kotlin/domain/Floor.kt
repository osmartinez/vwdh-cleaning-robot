package domain

data class Floor (val width: Int, val height: Int) {
    init {
        require(width >= 0 && height >= 0)
    }
    fun contains(p: Position): Boolean =
        p.x in 0..width && p.y in 0..height
}