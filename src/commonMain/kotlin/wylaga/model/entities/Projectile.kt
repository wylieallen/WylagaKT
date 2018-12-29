package wylaga.model.entities

import wylaga.util.DirectionVector

class Projectile(x: Double, y: Double, width: Double, height: Double, trajectory: DirectionVector, velocity: Double, orientation: Orientation,
                 private val onCollision: (Projectile, Ship) -> Unit, private val onExpire: (Projectile) -> Unit)
    : Entity(x, y, width, height, orientation, trajectory, velocity) {

    override fun expire() = onExpire(this)

    fun impact(ship: Ship) = onCollision(this, ship)
}