package wylaga.model.entities.projectiles

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector

open class Projectile(x: Double, y: Double, width: Double, height: Double, trajectory: DirectionVector, velocity: Double, orientation: Orientation,
                 private val onCollision: (Projectile, Ship) -> Unit, private val onDisable: (Projectile) -> Unit)
    : Entity(x, y, width, height, orientation, trajectory, velocity) {

    fun impact(ship: Ship) = onCollision(this, ship)
    fun disable() = onDisable(this)
}