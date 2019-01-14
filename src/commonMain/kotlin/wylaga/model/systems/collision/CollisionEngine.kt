package wylaga.model.systems.collision

import wylaga.model.entities.pickups.Pickup
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.Engine

class CollisionEngine(private val projectileMinY: Double = -100.0, private val projectileMaxY: Double = 1000.0,
                      private val playerMinX: Double = 0.0, private val playerMaxX: Double = 1550.0,
                      private val playerMinY: Double = 0.0, private val playerMaxY: Double = 850.0) : Engine {
    private val playerShips = mutableSetOf<Ship>()
    private val friendlyShips = mutableSetOf<Ship>()
    private val friendlyProjectiles = mutableSetOf<Projectile>()
    private val hostileShips = mutableSetOf<Ship>()
    private val hostileProjectiles = mutableSetOf<Projectile>()
    private val pickups = mutableSetOf<Pickup>()

    private val shipToShipListeners = mutableSetOf<(Ship, Ship) -> Unit>()
    private val hostileShipToProjectileListeners = mutableSetOf<(Projectile, Ship) -> Unit>()
    private val friendlyShipToProjectileListeners = mutableSetOf<(Projectile, Ship) -> Unit>()
    private val playerToPickupListeners = mutableSetOf<(Pickup, Ship) -> Unit>()

    fun addFriendly(ship: Ship) = friendlyShips.add(ship)
    fun addFriendly(projectile: Projectile) = friendlyProjectiles.add(projectile)

    fun addHostile(ship: Ship) = hostileShips.add(ship)
    fun addHostile(projectile: Projectile) = hostileProjectiles.add(projectile)

    fun removeFriendly(ship: Ship) = friendlyShips.remove(ship)
    fun removeFriendly(projectile: Projectile) = friendlyProjectiles.remove(projectile)

    fun addPlayer(ship: Ship) = playerShips.add(ship)
    fun removePlayer(ship: Ship) = playerShips.remove(ship)

    fun removeHostile(ship: Ship) = hostileShips.remove(ship)
    fun removeHostile(projectile: Projectile) = hostileProjectiles.remove(projectile)

    fun addPickup(pickup: Pickup) = pickups.add(pickup)
    fun removePickup(pickup: Pickup) = pickups.remove(pickup)

    override fun tick() {
        // Apply out-of-bounds constraints
        for(pickup in pickups) {
            if(pickup.y < projectileMinY || pickup.y > projectileMaxY) {
                pickup.disable()
            }
        }

        for(player in playerShips) {
            if(player.y < playerMinY) {
                player.y = playerMinY
            } else if(player.y > playerMaxY) {
                player.y = playerMaxY
            }

            if(player.x < playerMinX) {
                player.x = playerMinX
            } else if(player.x > playerMaxX) {
                player.x = playerMaxX
            }
        }

        for(projectile in friendlyProjectiles) {
            if(projectile.y < projectileMinY || projectile.y > projectileMaxY) {
                projectile.disable()
            }
        }

        for(projectile in hostileProjectiles) {
            if(projectile.y < projectileMinY || projectile.y > projectileMaxY) {
                projectile.disable()
            }
        }

        // Process collisions
        for(player in playerShips) {
            for(projectile in hostileProjectiles) {
                if(entitiesCollide(player, projectile)) {
                    friendlyShipToProjectileListeners.forEach{ it(projectile, player) }
                }
            }
            for(hostile in hostileShips) {
                if(entitiesCollide(player, hostile)) {
                    shipToShipListeners.forEach { it(player, hostile) }
                }
            }
            for(pickup in pickups) {
                if(entitiesCollide(player, pickup)) {
                    playerToPickupListeners.forEach{ it(pickup, player) }
                }
            }
        }

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
    fun subscribePlayerToPickup(listener: (Pickup, Ship) -> Unit) = playerToPickupListeners.add(listener)
}
