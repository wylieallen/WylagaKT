package wylaga.model.entities.ships

import wylaga.model.entities.Entity
import wylaga.model.entities.pilots.Pilot
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.weapons.NullWeapon
import wylaga.model.entities.weapons.Weapon
import wylaga.model.systems.boosting.Boostable
import wylaga.model.systems.damage.Damagable
import wylaga.model.systems.firing.Fireable
import wylaga.model.systems.piloting.Pilotable
import wylaga.util.DirectionVector

class Ship(x: Double, y: Double, width: Double, height: Double, velocity: Double, orientation: Orientation, var maxHealth: Double, var points: Int,
                private val onDeath: (Ship) -> Unit, hardpointX: Double, hardpointY: Double, var activePilot: Pilot, private val spawnProjectile: (Projectile, Any) -> Unit, weapon: Weapon = NullWeapon.INSTANCE)
    : Entity(x, y, width, height, velocity = velocity, orientation = orientation), Damagable, Fireable, Pilotable, Boostable {

    val hardpoint = Hardpoint(hardpointX, hardpointY, weapon)

    private val damageListeners = mutableSetOf<(Ship) -> Unit>()
    private val healListeners = mutableSetOf<(Ship) -> Unit>()
    private val fireListeners = mutableSetOf<(Ship) -> Unit>()
    private val trajectoryListeners = mutableSetOf<(Ship) -> Unit>()
    private val boostListeners = mutableSetOf<(Ship) -> Unit>()
    private val weaponChangeListeners = mutableSetOf<(Weapon) -> Unit>()

    fun subscribeDamage(callback: (Ship) -> Unit) = damageListeners.add(callback)
    fun subscribeHeal(callback: (Ship) -> Unit) = healListeners.add(callback)
    fun subscribeFire(callback: (Ship) -> Unit) = fireListeners.add(callback)
    fun subscribeTrajectory(callback: (Ship) -> Unit) = trajectoryListeners.add(callback)
    fun subscribeBoost(callback: (Ship) -> Unit) = boostListeners.add(callback)
    fun subscribeWeaponChange(callback: (Weapon) -> Unit) = weaponChangeListeners.add(callback)

    override var trajectory: DirectionVector
        get() = super.trajectory
        set(value) {
            val prevSign = getSign(super.trajectory.dy)
            val nextSign = getSign(value.dy)
            super.trajectory = value
            if(prevSign != nextSign)
                trajectoryListeners.forEach { it(this) }
        }

    val baseVelocity = velocity
    val boostVelocity = velocity * 2

    var wantsToFire = false
    var wantsToBoost = false
        set(value) {
            field = value
            boostListeners.forEach { it(this) }
        }

    var health = maxHealth
        private set(nextHealth) {
            field = nextHealth
            if(health <= 0) {
                onDeath(this)
            } else if(health > maxHealth) {
                field = maxHealth
            }
        }

    val maxEnergy = 200.0

    var energy = maxEnergy
        set(nextFuel) {
            field = nextFuel
            if(energy < 0) {
                field = 0.0
            } else if (energy > maxEnergy) {
                field = maxEnergy
            }
        }

    override fun boost() {
        if(wantsToBoost) {
            if(energy > 0) {
                velocity = boostVelocity
                energy -= 2
            } else {
                velocity = baseVelocity
                energy -= 1
            }
        } else {
            velocity = baseVelocity
            energy += 2
        }
    }

    override fun damage(damage: Double) { health -= damage; damageListeners.forEach{ it(this) } }
    override fun heal(healing: Double) { health += healing; healListeners.forEach{ it(this) } }

    override fun fire() {
        if(wantsToFire) {
            hardpoint.weapon.fire(this, hardpoint.x, hardpoint.y).forEach{spawnProjectile(it, hardpoint.weapon)}
            fireListeners.forEach{ it(this) }
        }
    }

    override fun pilot() { activePilot.update(this) }

    private fun getSign(delta: Double): Sign {
        return when {
            delta < 0 -> Sign.NEGATIVE
            delta > 0 -> Sign.POSITIVE
            else -> Sign.ZERO
        }
    }

    inner class Hardpoint(val x: Double, val y: Double, weapon: Weapon = NullWeapon.INSTANCE) {
        var weapon = weapon
            set(value) {
                field = value
                weaponChangeListeners.forEach{ it(weapon) }
            }
    }

    private enum class Sign {
        NEGATIVE, ZERO, POSITIVE
    }
}