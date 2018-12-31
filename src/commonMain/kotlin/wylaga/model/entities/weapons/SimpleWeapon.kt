package wylaga.model.entities.weapons

import wylaga.model.entities.Projectile
import wylaga.model.entities.ships.Ship

class SimpleWeapon(private val projectileWidth: Double, private val projectileHeight: Double, private val projectileVelocity: Double,
                  private val onImpact: (Projectile, Ship) -> Unit, private val onProjectileDespawn: (Projectile) -> Unit) : Weapon {

    override fun fire(ship: Ship, hardpointX: Double, hardpointY: Double): Collection<Projectile> {
        return setOf(
            Projectile(ship.x + hardpointX, ship.y + hardpointY, projectileWidth, projectileHeight,
                ship.orientation.vector, projectileVelocity, ship.orientation, onImpact, onProjectileDespawn)
        )
    }
}