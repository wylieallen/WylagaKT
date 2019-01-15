package wylaga.model.systems.expiration

import wylaga.model.entities.pickups.Pickup
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.Engine

class ExpirationEngine : Engine {

    private val players = mutableSetOf<Ship>()
    private val friendlyShips = mutableSetOf<Ship>()
    private val hostileShips = mutableSetOf<Ship>()
    private val friendlyProjectiles = mutableSetOf<Projectile>()
    private val hostileProjectiles = mutableSetOf<Projectile>()
    private val pickups = mutableSetOf<Pickup>()

    private val playerListeners = mutableSetOf<(Ship) -> Unit>()
    private val friendlyShipListeners = mutableSetOf<(Ship) -> Unit>()
    private val hostileShipListeners = mutableSetOf<(Ship) -> Unit>()
    private val friendlyProjectileListeners = mutableSetOf<(Projectile) -> Unit>()
    private val hostileProjectileListeners = mutableSetOf<(Projectile) -> Unit>()
    private val pickupListeners = mutableSetOf<(Pickup) -> Unit>()

    fun addPlayer(player: Ship) = players.add(player)
    fun addFriendly(ship: Ship) = friendlyShips.add(ship)
    fun addHostile(ship: Ship) = hostileShips.add(ship)
    fun addFriendly(projectile: Projectile) = friendlyProjectiles.add(projectile)
    fun addHostile(projectile: Projectile) = hostileProjectiles.add(projectile)
    fun addPickup(pickup: Pickup) = pickups.add(pickup)

    fun subscribePlayer(callback: (Ship) -> Unit) = playerListeners.add(callback)
    fun subscribeFriendlyShip(callback: (Ship) -> Unit) = friendlyShipListeners.add(callback)
    fun subscribeHostileShip(callback: (Ship) -> Unit) = hostileShipListeners.add(callback)
    fun subscribeFriendly(callback: (Projectile) -> Unit) = friendlyProjectileListeners.add(callback)
    fun subscribeHostile(callback: (Projectile) -> Unit) = hostileProjectileListeners.add(callback)
    fun subscribePickup(callback: (Pickup) -> Unit) = pickupListeners.add(callback)

    fun unsubscribeHostile(callback: (Ship) -> Unit) = hostileShipListeners.remove(callback)

    override fun tick() {
        players.forEach{playerListeners.forEach{callback -> callback(it)}}
        friendlyShips.forEach{friendlyShipListeners.forEach{callback -> callback(it)}}
        hostileShips.forEach{hostileShipListeners.forEach{callback -> callback(it)}}
        friendlyProjectiles.forEach{friendlyProjectileListeners.forEach{callback -> callback(it)}}
        hostileProjectiles.forEach{hostileProjectileListeners.forEach{callback -> callback(it)}}
        pickups.forEach{pickupListeners.forEach{callback -> callback(it)}}

        players.clear()
        friendlyShips.clear()
        hostileShips.clear()
        friendlyProjectiles.clear()
        hostileProjectiles.clear()
        pickups.clear()
    }
}