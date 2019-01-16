package wylaga.model.systems.expiration

import wylaga.model.entities.pickups.Pickup
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.Engine

class ExpirationEngine : Engine {

    private val players = mutableSetOf<Ship>()
    private val friendlyShips = mutableSetOf<Ship>()
    private val hostileShips = mutableSetOf<Ship>()
    private val friendlyProjectiles = mutableSetOf<Pair<Projectile, Cause>>()
    private val hostileProjectiles = mutableSetOf<Pair<Projectile, Cause>>()
    private val pickups = mutableSetOf<Pair<Pickup, Cause>>()

    private val playerListeners = mutableSetOf<(Ship) -> Unit>()
    private val friendlyShipListeners = mutableSetOf<(Ship) -> Unit>()
    private val hostileShipListeners = mutableSetOf<(Ship) -> Unit>()
    private val friendlyProjectileListeners = mutableSetOf<(Projectile, cause: Cause) -> Unit>()
    private val hostileProjectileListeners = mutableSetOf<(Projectile, cause: Cause) -> Unit>()
    private val pickupListeners = mutableSetOf<(Pickup, cause: Cause) -> Unit>()

    fun addPlayer(player: Ship) = players.add(player)
    fun addFriendly(ship: Ship) = friendlyShips.add(ship)
    fun addHostile(ship: Ship) = hostileShips.add(ship)
    fun addFriendly(projectile: Projectile, cause: Cause) = friendlyProjectiles.add(Pair(projectile, cause))
    fun addHostile(projectile: Projectile, cause: Cause) = hostileProjectiles.add(Pair(projectile, cause))
    fun addPickup(pickup: Pickup, cause: Cause) = pickups.add(Pair(pickup, cause))

    fun subscribePlayer(callback: (Ship) -> Unit) = playerListeners.add(callback)
    fun subscribeFriendlyShip(callback: (Ship) -> Unit) = friendlyShipListeners.add(callback)
    fun subscribeHostileShip(callback: (Ship) -> Unit) = hostileShipListeners.add(callback)
    fun subscribeFriendly(callback: (Projectile, Cause) -> Unit) = friendlyProjectileListeners.add(callback)
    fun subscribeHostile(callback: (Projectile, Cause) -> Unit) = hostileProjectileListeners.add(callback)
    fun subscribePickup(callback: (Pickup, Cause) -> Unit) = pickupListeners.add(callback)

    fun unsubscribeHostile(callback: (Ship) -> Unit) = hostileShipListeners.remove(callback)

    override fun tick() {
        players.forEach{playerListeners.forEach{callback -> callback(it)}}
        friendlyShips.forEach{friendlyShipListeners.forEach{callback -> callback(it)}}
        hostileShips.forEach{hostileShipListeners.forEach{callback -> callback(it)}}
        friendlyProjectiles.forEach{friendlyProjectileListeners.forEach{callback -> callback(it.first, it.second)}}
        hostileProjectiles.forEach{hostileProjectileListeners.forEach{callback -> callback(it.first, it.second)}}
        pickups.forEach{pickupListeners.forEach{callback -> callback(it.first, it.second)}}

        players.clear()
        friendlyShips.clear()
        hostileShips.clear()
        friendlyProjectiles.clear()
        hostileProjectiles.clear()
        pickups.clear()
    }
}