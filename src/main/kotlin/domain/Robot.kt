package domain

data class Robot (val position: Position, val orientation: Orientation, val floor: Floor){

    fun rotateClockwise(): Robot = copy(orientation= orientation.clockwise())
    fun rotateAnticlockwise(): Robot = copy(orientation= orientation.anticlockwise())
    fun move(): Robot = copy(position = position.forward(orientation))
}