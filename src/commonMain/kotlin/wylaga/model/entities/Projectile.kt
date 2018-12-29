package wylaga.model.entities

import wylaga.util.DirectionVector

class Projectile(x: Double, y: Double, width: Double, height: Double, trajectory: DirectionVector, velocity: Double,
                 private val onCollision: (Projectile, Ship) -> Unit, private val onExpire: (Projectile) -> Unit) : Entity(x, y, width, height, trajectory, velocity) {

    override fun expire() = onExpire(this)

    fun impact(ship: Ship) = onCollision(this, ship)
}