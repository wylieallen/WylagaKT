package wylaga.model.systems.collision

import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.Engine

class CollisionEngine(private val minY: Double = -100.0, private val maxY: Double = 1000.0) : Engine {
    private val friendlyShips = mutableSetOf<Ship>()
    private val friendlyProjectiles = mutableSetOf<Projectile>()
    private val hostileShips = mutableSetOf<Ship>()
    private val hostileProjectiles = mutableSetOf<Projectile>()

    private val shipToShipListeners = mutableSetOf<(Ship, Ship) -> Unit>()
    private val hostileShipToProjectileListeners = mutableSetOf<(Projectile, Ship) -> Unit>()
    private val friendlyShipToProjectileListeners = mutableSetOf<(Projectile, Ship) -> Unit>()

    fun addFriendly(ship: Ship) = friendlyShips.add(ship)
    fun addFriendly(projectile: Projectile) = friendlyProjectiles.add(projectile)

    fun addHostile(ship: Ship) = hostileShips.add(ship)
    fun addHostile(projectile: Projectile) = hostileProjectiles.add(projectile)

    fun removeFriendly(ship: Ship) = friendlyShips.remove(ship)
    fun removeFriendly(projectile: Projectile) = friendlyProjectiles.remove(projectile)

    fun removeHostile(ship: Ship) = hostileShips.remove(ship)
    fun removeHostile(projectile: Projectile) = hostileProjectiles.remove(projectile)

    override fun tick() {

        for(friendly in friendlyShips) {
            for(projectile in hostileProjectiles) {
                if(entitiesCollide(friendly, projectile)) {
                    friendlyShipToProjectileListeners.forEach{ it(projectile, friendly) }
                }
            }
            for(hostile in hostileShips) {
                if(entitiesCollide(friendly, hostile)) {
                    shipToShipListeners.forEach { it(friendly, hostile) }
                }
            }
        }

        for(hostile in hostileShips) {
            for(projectile in friendlyProjectiles) {
                if(entitiesCollide(projectile, hostile)) {
                    hostileShipToProjectileListeners.forEach{ it(projectile, hostile) }
                }
            }
        }

        for(projectile in friendlyProjectiles) {
            if(projectile.y < minY || projectile.y > maxY) {
                projectile.disable()
            }
        }

        for(projectile in hostileProjectiles) {
            if(projectile.y < minY || projectile.y > maxY) {
                projectile.disable()
            }
        }
    }

    private fun entitiesCollide(a: Collidable, b: Collidable) : Boolean {
        if(a == b) return false

        return if((a.minY > b.maxY) || (b.minY > a.maxY)) {
            false
        } else !((a.minX > b.maxX) || (b.minX > a.maxX))
    }

    fun subscribeHostileShipToProjectile(listener: (Projectile, Ship) -> Unit) = hostileShipToProjectileListeners.add(listener)
    fun subscribeFriendlyShipToProjectile(listener: (Projectile, Ship) -> Unit) = friendlyShipToProjectileListeners.add(listener)
    fun subscribeShipToShip(listener: (Ship, Ship) -> Unit) = shipToShipListeners.add(listener)
}
