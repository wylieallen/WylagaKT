package wylaga.model.systems.collision

import wylaga.model.entities.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.Engine

class CollisionEngine : Engine {
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
                    onFriendlyShipHit(projectile, friendly)
                }
            }
            for(hostile in hostileShips) {
                if(entitiesCollide(friendly, hostile)) {
                    onCollision(friendly, hostile)
                }
            }
        }

        for(hostile in hostileShips) {
            for(projectile in friendlyProjectiles) {
                if(entitiesCollide(projectile, hostile)) {
                    onHostileShipHit(projectile, hostile)
                }
            }
        }
    }

    fun entitiesCollide(a: Collidable, b: Collidable) : Boolean {
        if(a == b) return false

        return if((a.minY > b.maxY) || (b.minY > a.maxY)) {
            false
        } else !((a.minX > b.maxX) || (b.minX > a.maxX))
    }

    fun onCollision(a: Ship, b: Ship) = shipToShipListeners.forEach { it(a, b) }
    fun onFriendlyShipHit(a: Projectile, b: Ship) = friendlyShipToProjectileListeners.forEach{ it(a, b) }
    fun onHostileShipHit(a: Projectile, b: Ship) = hostileShipToProjectileListeners.forEach{ it(a, b) }

    fun subscribeHostileShipToProjectile(listener: (Projectile, Ship) -> Unit) = hostileShipToProjectileListeners.add(listener)
    fun subscribeFriendlyShipToProjectile(listener: (Projectile, Ship) -> Unit) = friendlyShipToProjectileListeners.add(listener)
    fun subscribeShipToShip(listener: (Ship, Ship) -> Unit) = shipToShipListeners.add(listener)
}
