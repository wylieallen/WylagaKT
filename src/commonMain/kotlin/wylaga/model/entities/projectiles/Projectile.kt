package wylaga.model.entities.projectiles

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.expiration.Cause
import wylaga.util.DirectionVector

open class Projectile(x: Double, y: Double, width: Double, height: Double, trajectory: DirectionVector, velocity: Double, orientation: Orientation,
                 private val onCollision: (Projectile, Ship) -> Unit, private val onDisable: (Projectile, Cause) -> Unit)
    : Entity(x, y, width, height, orientation, trajectory, velocity) {

    fun impact(ship: Ship) = onCollision(this, ship)
    fun disable(cause: Cause) = onDisable(this, cause)
}