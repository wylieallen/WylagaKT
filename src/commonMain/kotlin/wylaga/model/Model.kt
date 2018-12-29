package wylaga.model

import wylaga.model.entities.Projectile
import wylaga.model.entities.Ship
import wylaga.model.systems.collision.CollisionEngine
import wylaga.model.systems.expiration.Expirable
import wylaga.model.systems.expiration.ExpirationEngine
import wylaga.model.systems.firing.Fireable
import wylaga.model.systems.firing.FiringEngine
import wylaga.model.systems.movement.MovementEngine

class Model {
    private val collisionEngine = CollisionEngine()
    private val movementEngine = MovementEngine()
    private val firingEngine = FiringEngine()
    private val expirationEngine = ExpirationEngine()
    private val engines = linkedSetOf(movementEngine, collisionEngine, firingEngine, expirationEngine)

    private val shipSpawnListeners = mutableSetOf<(Ship) -> Unit>()
    private val shipDespawnListeners = mutableSetOf<(Ship) -> Unit>()

    private val projectileSpawnListeners = mutableSetOf<(Projectile, Fireable) -> Unit>()
    private val projectileDespawnListeners = mutableSetOf<(Projectile) -> Unit>()

    init {
        subscribeShipSpawn { movementEngine.add(it); collisionEngine.add(it); firingEngine.add(it); }
        subscribeShipDespawn { movementEngine.remove(it); collisionEngine.remove(it); firingEngine.remove(it); }

        subscribeProjectileSpawn { projectile: Projectile, _: Fireable -> movementEngine.add(projectile); collisionEngine.add(projectile); }
        subscribeProjectileDespawn { movementEngine.remove(it); collisionEngine.remove(it); }

        collisionEngine.subscribeShipToShip { a: Ship, b: Ship -> a.damage(30.0); b.damage(30.0); }
        collisionEngine.subscribeShipToProjectile(Projectile::impact)
    }

    fun tick() = engines.forEach { it.tick() }

    fun flagForExpiration(expirable: Expirable) = expirationEngine.add(expirable)

    fun spawnShip(ship: Ship) = shipSpawnListeners.forEach { it(ship) }
    fun despawnShip(ship: Ship) = shipDespawnListeners.forEach { it(ship) }

    fun spawnProjectile(projectile: Projectile, source: Fireable) = projectileSpawnListeners.forEach { it(projectile, source) }
    fun despawnProjectile(projectile: Projectile) = projectileDespawnListeners.forEach { it(projectile) }

    fun subscribeShipSpawn(callback: (Ship) -> Unit) = shipSpawnListeners.add(callback)
    fun subscribeShipDespawn(callback: (Ship) -> Unit) = shipDespawnListeners.add(callback)

    fun subscribeProjectileSpawn(callback: (Projectile, Fireable) -> Unit) = projectileSpawnListeners.add(callback)
    fun subscribeProjectileDespawn(callback: (Projectile) -> Unit) = projectileDespawnListeners.add(callback)
}