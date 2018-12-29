package wylaga.util

import kotlin.math.sqrt

class DirectionVector(dx: Double, dy: Double) {
    val dx: Double
    val dy: Double

    init {
        val mag = sqrt(dx * dx + dy * dy)
        if(mag <= 1.0) {
            this.dx = dx
            this.dy = dy
        } else {
            this.dx = dx / mag
            this.dy = dy / mag
        }
    }

    companion object {
        val NULL = DirectionVector(0.0, 0.0)

        val NORTH = DirectionVector(0.0, -1.0)
        val NORTHEAST = DirectionVector(1.0, -1.0)
        val EAST = DirectionVector(1.0, 0.0)
        val SOUTHEAST = DirectionVector(1.0, 1.0)
        val SOUTH = DirectionVector(0.0, 1.0)
        val SOUTHWEST = DirectionVector(-1.0, 1.0)
        val WEST = DirectionVector(-1.0, 0.0)
        val NORTHWEST = DirectionVector(-1.0, -1.0)
    }
}