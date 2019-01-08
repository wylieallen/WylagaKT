package wylaga.model

import wylaga.model.entities.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.Engine
import wylaga.model.systems.boosting.BoostingEngine
import wylaga.model.systems.collision.CollisionEngine
import wylaga.model.systems.expiration.Expirable
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

    private val friendlyShipSpawnListeners = mutableSetOf<(Ship) -> Unit>()
    private val hostileShipSpawnListeners = mutableSetOf<(Ship) -> Unit>()

    private val friendlyShipDespawnListeners = mutableSetOf<(Ship) -> Unit>()
    private val hostileShipDespawnListeners = mutableSetOf<(Ship) -> Unit>()

    private val friendlyProjectileSpawnListeners = mutableSetOf<(Projectile, Any) -> Unit>()
    private val hostileProjectileSpawnListeners = mutableSetOf<(Projectile, Any) -> Unit>()

    private val friendlyProjectileDespawnListeners = mutableSetOf<(Projectile) -> Unit>()
    private val hostileProjectileDespawnListeners = mutableSetOf<(Projectile) -> Unit>()

    init {
        subscribeFriendlyShipSpawn { movementEngine.add(it); collisionEngine.addFriendly(it); firingEngine.add(it); pilotingEngine.add(it); boostingEngine.add(it);  }
        subscribeFriendlyShipDespawn { movementEngine.remove(it); collisionEngine.removeFriendly(it); firingEngine.remove(it); pilotingEngine.remove(it); boostingEngine.remove(it); }

        subscribeHostileShipSpawn { movementEngine.add(it); collisionEngine.addHostile(it); firingEngine.add(it); pilotingEngine.add(it); boostingEngine.add(it); }
        subscribeHostileShipDespawn { movementEngine.remove(it); collisionEngine.removeHostile(it); firingEngine.remove(it); pilotingEngine.remove(it); boostingEngine.remove(it); }

        subscribeFriendlyProjectileSpawn { projectile: Projectile, _ -> movementEngine.add(projectile); collisionEngine.addFriendly(projectile); }
        subscribeFriendlyProjectileDespawn { movementEngine.remove(it); collisionEngine.removeFriendly(it); }

        subscribeHostileProjectileSpawn { projectile: Projectile, _ -> movementEngine.add(projectile); collisionEngine.addHostile(projectile); }
        subscribeHostileProjectileDespawn { movementEngine.remove(it); collisionEngine.removeHostile(it); }

        collisionEngine.subscribeShipToShip { a: Ship, b: Ship -> a.damage(30.0); b.damage(30.0); }
        collisionEngine.subscribeFriendlyShipToProjectile(Projectile::impact)
        collisionEngine.subscribeHostileShipToProjectile(Projectile::impact)
    }

    fun tick() = engines.forEach(Engine::tick)

    fun flagForExpiration(expirable: Expirable) = expirationEngine.add(expirable)

    fun spawnFriendlyShip(ship: Ship) = friendlyShipSpawnListeners.forEach { it(ship) }
    fun spawnHostileShip(ship: Ship) = hostileShipSpawnListeners.forEach{ it(ship) }

    fun despawnFriendlyShip(ship: Ship) = friendlyShipDespawnListeners.forEach { it(ship) }
    fun despawnHostileShip(ship: Ship) = hostileShipDespawnListeners.forEach { it(ship) }

    fun spawnFriendlyProjectile(projectile: Projectile, source: Any) = friendlyProjectileSpawnListeners.forEach { it(projectile, source) }
    fun despawnFriendlyProjectile(projectile: Projectile) = friendlyProjectileDespawnListeners.forEach { it(projectile) }

    fun spawnHostileProjectile(projectile: Projectile, source: Any) = hostileProjectileSpawnListeners.forEach { it(projectile, source) }
    fun despawnHostileProjectile(projectile: Projectile) = hostileProjectileDespawnListeners.forEach { it(projectile) }

    fun subscribeFriendlyShipSpawn(callback: (Ship) -> Unit) = friendlyShipSpawnListeners.add(callback)
    fun subscribeFriendlyShipDespawn(callback: (Ship) -> Unit) = friendlyShipDespawnListeners.add(callback)
    fun unsubscribeFriendlyShipDespawn(callback: (Ship) -> Unit) = friendlyShipDespawnListeners.remove(callback)

    fun subscribeHostileShipSpawn(callback: (Ship) -> Unit) = hostileShipSpawnListeners.add(callback)
    fun subscribeHostileShipDespawn(callback: (Ship) -> Unit) = hostileShipDespawnListeners.add(callback)
    fun unsubscribeHostileShipDespawn(callback: (Ship) -> Unit) = hostileShipDespawnListeners.remove(callback)


    fun subscribeFriendlyProjectileSpawn(callback: (Projectile, Any) -> Unit) = friendlyProjectileSpawnListeners.add(callback)
    fun subscribeFriendlyProjectileDespawn(callback: (Projectile) -> Unit) = friendlyProjectileDespawnListeners.add(callback)
    fun subscribeHostileProjectileSpawn(callback: (Projectile, Any) -> Unit) = hostileProjectileSpawnListeners.add(callback)
    fun subscribeHostileProjectileDespawn(callback: (Projectile) -> Unit) = hostileProjectileDespawnListeners.add(callback)

}