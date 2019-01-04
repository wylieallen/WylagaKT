package wylaga.model.entities.ships

import wylaga.model.entities.Entity
import wylaga.model.entities.pilots.Pilot
import wylaga.model.systems.damage.Damagable
import wylaga.model.systems.firing.Fireable
import wylaga.model.systems.piloting.Pilotable

open class Ship(x: Double, y: Double, width: Double, height: Double, velocity: Double, orientation: Orientation, private val maxHealth: Double,
                private val onDeath: (Ship) -> Unit, private val onExpire: (Ship) -> Unit, private val onFire: (Ship) -> Unit, private val pilot: Pilot)
    : Entity(x, y, width, height, velocity = velocity, orientation = orientation), Damagable, Fireable, Pilotable {

    var wantsToFire = false

    var curHealth = maxHealth
        private set

    override fun damage(damage: Double) {
        curHealth -= damage
        if(curHealth <= 0) {
            onDeath(this)
        }
    }

    override fun heal(healing: Double) {
        curHealth += healing
        if(curHealth >= maxHealth) {
            curHealth = maxHealth
        }
    }

    override fun expire() = onExpire(this)
    override fun fire() = if(wantsToFire) onFire(this) else {}
    override fun pilot() { pilot.update(this) }
}