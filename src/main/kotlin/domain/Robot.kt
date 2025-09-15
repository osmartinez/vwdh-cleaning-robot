package domain

data class Robot (val position: Position, val orientation: Orientation){

    fun rotateClockwise(): Robot = copy(orientation= orientation.clockwise())

}