package wylaga.model

import wylaga.model.entities.pickups.Pickup
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.Engine
import wylaga.model.systems.boosting.BoostingEngine
import wylaga.model.systems.collision.CollisionEngine
import wylaga.model.systems.expiration.Cause
import wylaga.model.systems.expiration.ExpirationEngine
import wylaga.model.systems.firing.FiringEngine
import wylaga.model.systems.movement.MovementEngine
import wylaga.model.systems.piloting.PilotingEngine

class Model {

    private val collisionEngine = CollisionEngine()
    private val movementEngine = MovementEngine()
    private val firingEngine = FiringEngine()
    private val expirationEngine = ExpirationEngine()
    private val pilotingEngine = PilotingEngine()
    private val boostingEngine = BoostingEngine()
    private val engines = linkedSetOf(pilotingEngine, boostingEngine, movementEngine, collisionEngine, firingEngine, expirationEngine)

    private val playerShipSpawnListeners = mutableSetOf<(Ship) -> Unit>()
    private val friendlyShipSpawnListeners = mutableSetOf<(Ship) -> Unit>()
    private val hostileShipSpawnListeners = mutableSetOf<(Ship) -> Unit>()

    private val friendlyProjectileSpawnListeners = mutableSetOf<(Projectile, Any) -> Unit>()
    private val hostileProjectileSpawnListeners = mutableSetOf<(Projectile, Any) -> Unit>()

    private val pickupSpawnListeners = mutableSetOf<(Pickup) -> Unit>()

    init {
        subscribePlayerShipSpawn { movementEngine.add(it); collisionEngine.addPlayer(it); firingEngine.add(it); pilotingEngine.add(it); boostingEngine.add(it); }
        subscribePlayerShipDespawn { movementEngine.remove(it); collisionEngine.removePlayer(it); firingEngine.remove(it); pilotingEngine.remove(it); boostingEngine.remove(it); }

        subscribeFriendlyShipSpawn { movementEngine.add(it); collisionEngine.addFriendly(it); firingEngine.add(it); pilotingEngine.add(it); boostingEngine.add(it);  }
        subscribeFriendlyShipDespawn { movementEngine.remove(it); collisionEngine.removeFriendly(it); firingEngine.remove(it); pilotingEngine.remove(it); boostingEngine.remove(it); }

        subscribeHostileShipSpawn { movementEngine.add(it); collisionEngine.addHostile(it); firingEngine.add(it); pilotingEngine.add(it); boostingEngine.add(it); }
        subscribeHostileShipDespawn { movementEngine.remove(it); collisionEngine.removeHostile(it); firingEngine.remove(it); pilotingEngine.remove(it); boostingEngine.remove(it); }

        subscribeFriendlyProjectileSpawn { projectile: Projectile, _ -> movementEngine.add(projectile); collisionEngine.addFriendly(projectile); }
        subscribeFriendlyProjectileDespawn { projectile, _ -> movementEngine.remove(projectile); collisionEngine.removeFriendly(projectile); }

        subscribeHostileProjectileSpawn { projectile: Projectile, _ -> movementEngine.add(projectile); collisionEngine.addHostile(projectile); }
        subscribeHostileProjectileDespawn { projectile, _ -> movementEngine.remove(projectile); collisionEngine.removeHostile(projectile); }

        subscribePickupSpawn { movementEngine.add(it); collisionEngine.addPickup(it); }
        subscribePickupDespawn { pickup, _ -> movementEngine.remove(pickup); collisionEngine.removePickup(pickup); }

        collisionEngine.subscribeShipToShip { a: Ship, b: Ship -> a.damage(30.0); b.damage(30.0); }
        collisionEngine.subscribeFriendlyShipToProjectile(Projectile::impact)
        collisionEngine.subscribeHostileShipToProjectile(Projectile::impact)
        collisionEngine.subscribePlayerToPickup{pickup, ship -> pickup.effect(ship); pickup.disable(Cause.IMPACT); }
    }

    fun tick() = engines.forEach(Engine::tick)

    fun spawnPlayerShip(ship: Ship) = playerShipSpawnListeners.forEach { it(ship) }
    fun spawnFriendlyShip(ship: Ship) = friendlyShipSpawnListeners.forEach { it(ship) }
    fun spawnHostileShip(ship: Ship) = hostileShipSpawnListeners.forEach { it(ship) }

    fun despawnPlayerShip(ship: Ship) = expirationEngine.addPlayer(ship)
    fun despawnFriendlyShip(ship: Ship) = expirationEngine.addFriendly(ship)
    fun despawnHostileShip(ship: Ship) = expirationEngine.addHostile(ship)

    fun spawnFriendlyProjectile(projectile: Projectile, source: Any) = friendlyProjectileSpawnListeners.forEach { it(projectile, source) }
    fun despawnFriendlyProjectile(projectile: Projectile, cause: Cause) = expirationEngine.addFriendly(projectile, cause)

    fun spawnHostileProjectile(projectile: Projectile, source: Any) = hostileProjectileSpawnListeners.forEach { it(projectile, source) }
    fun despawnHostileProjectile(projectile: Projectile, cause: Cause) = expirationEngine.addHostile(projectile, cause)

    fun spawnPickup(pickup: Pickup) = pickupSpawnListeners.forEach { it(pickup) }
    fun despawnPickup(pickup: Pickup, cause: Cause) = expirationEngine.addPickup(pickup, cause)

    fun subscribePlayerShipSpawn(callback: (Ship) -> Unit) = playerShipSpawnListeners.add(callback)
    fun subscribePlayerShipDespawn(callback: (Ship) -> Unit) = expirationEngine.subscribePlayer(callback)
    fun subscribeFriendlyShipSpawn(callback: (Ship) -> Unit) = friendlyShipSpawnListeners.add(callback)
    fun subscribeFriendlyShipDespawn(callback: (Ship) -> Unit) =  expirationEngine.subscribeFriendlyShip(callback)

    fun subscribeHostileShipSpawn(callback: (Ship) -> Unit) = hostileShipSpawnListeners.add(callback)
    fun subscribeHostileShipDespawn(callback: (Ship) -> Unit) = expirationEngine.subscribeHostileShip(callback)
    fun unsubscribeHostileShipDespawn(callback: (Ship) -> Unit) = expirationEngine.unsubscribeHostile(callback)

    fun subscribeFriendlyProjectileSpawn(callback: (Projectile, Any) -> Unit) = friendlyProjectileSpawnListeners.add(callback)
    fun subscribeFriendlyProjectileDespawn(callback: (Projectile, Cause) -> Unit) = expirationEngine.subscribeFriendly(callback)
    fun subscribeHostileProjectileSpawn(callback: (Projectile, Any) -> Unit) = hostileProjectileSpawnListeners.add(callback)
    fun subscribeHostileProjectileDespawn(callback: (Projectile, Cause) -> Unit) = expirationEngine.subscribeHostile(callback)

    fun subscribePickupSpawn(callback: (Pickup) -> Unit) = pickupSpawnListeners.add(callback)
    fun subscribePickupDespawn(callback: (Pickup, Cause) -> Unit) = expirationEngine.subscribePickup(callback)
}