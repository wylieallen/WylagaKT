package wylaga.model.entities.weapons

import wylaga.model.entities.Entity
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship

class SimpleWeapon(private val projectileWidth: Double, private val projectileHeight: Double, private val projectileVelocity: Double,
                   private val onImpact: (Projectile, Ship) -> Unit, private val onProjectileDespawn: (Projectile) -> Unit,
                   private val onDisable: (Projectile) -> Unit) : Weapon {

    private val centerX = projectileWidth / 2

    private fun calculateProjectileY(ship: Ship, hardpointY: Double): Double {
        return when (ship.orientation) {
            Entity.Orientation.NORTH -> ship.y - (projectileHeight + hardpointY)
            Entity.Orientation.SOUTH -> ship.y + ship.height + hardpointY
        }
    }

    override fun fire(ship: Ship, hardpointX: Double, hardpointY: Double): Collection<Projectile> {
        return setOf(
            Projectile(
                ship.x + hardpointX - centerX,
                calculateProjectileY(ship, hardpointY),
                projectileWidth,
                projectileHeight,
                ship.orientation.vector,
                projectileVelocity,
                ship.orientation,
                onImpact,
                onProjectileDespawn,
                onDisable
            )
        )
    }
}