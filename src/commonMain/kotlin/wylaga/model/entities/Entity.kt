package wylaga.model.entities

import wylaga.model.systems.collision.Collidable
import wylaga.model.systems.expiration.Expirable
import wylaga.model.systems.movement.Movable
import wylaga.util.DirectionVector
import kotlin.math.atan2

abstract class Entity(override var x: Double, override var y: Double,
                      var width: Double, var height: Double,
                      val orientation: Orientation,
                      override var trajectory: DirectionVector = DirectionVector.NULL,
                      override var velocity: Double = 0.0) : Movable, Collidable, Expirable {
    override val minX: Double
        get() = x
    override val maxX: Double
        get() = x + width
    override val minY: Double
        get() = y
    override val maxY: Double
        get() = y + height

    enum class Orientation(vector: DirectionVector) {
        NORTH(DirectionVector.NORTH), SOUTH(DirectionVector.SOUTH);

        val radians = atan2(vector.dx, vector.dy)
    }
}