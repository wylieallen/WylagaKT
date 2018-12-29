package wylaga.model.systems.collision

import wylaga.model.entities.Projectile
import wylaga.model.entities.Ship
import wylaga.model.systems.Engine

class CollisionEngine : Engine {
    private val ships = mutableSetOf<Ship>()
    private val projectiles = mutableSetOf<Projectile>()

    private val shipToShipListeners = mutableSetOf<(Ship, Ship) -> Unit>()
    private val shipToProjectileListeners = mutableSetOf<(Projectile, Ship) -> Unit>()

    fun add(ship: Ship) = ships.add(ship)
    fun remove(ship: Ship) = ships.remove(ship)

    fun add(projectile: Projectile) = projectiles.add(projectile)
    fun remove(projectile: Projectile) = projectiles.remove(projectile)

    override fun tick() {
        for(a in ships) {
            for(b in ships) {
                if(a != b && entitiesCollide(a, b)) {
                    onCollision(a, b)
                }
            }
            for(p in projectiles) {
                if(entitiesCollide(a, p)) {
                    onCollision(p, a)
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
    fun onCollision(a: Projectile, b: Ship) = shipToProjectileListeners.forEach { it(a, b) }

    fun subscribeShipToProjectile(listener: (Projectile, Ship) -> Unit) = shipToProjectileListeners.add(listener)
    fun subscribeShipToShip(listener: (Ship, Ship) -> Unit) = shipToShipListeners.add(listener)
}
