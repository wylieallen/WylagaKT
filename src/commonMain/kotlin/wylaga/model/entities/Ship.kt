package wylaga.model.entities

import wylaga.model.systems.damage.Damagable
import wylaga.model.systems.firing.Fireable

class Ship(x: Double, y: Double, width: Double, height: Double, velocity: Double, orientation: Orientation, private val maxHealth: Double,
           private val onDeath: (Ship) -> Unit, private val onExpire: (Ship) -> Unit, private val onFire: (Ship) -> Unit)
    : Entity(x, y, width, height, velocity = velocity, orientation = orientation), Damagable, Fireable {

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
}