package wylaga.model.entities.pickups

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector

class Pickup(x: Double, y: Double, width: Double, height: Double, trajectory: DirectionVector, velocity: Double, private val onDisable: (Pickup) -> Unit, private val onExpire: (Pickup) -> Unit, val effect: Effect)
    : Entity(x, y, width, height, Orientation.NORTH, trajectory, velocity) {

    override fun expire() = onExpire(this)

    fun disable() = onDisable(this)

    enum class Effect(private val effect: (Ship) -> Unit) {
        HEALING({it.heal(it.maxHealth * 0.2)}), ENERGY({it.energy += (it.maxEnergy * 0.2)});

        operator fun invoke(ship: Ship) = effect(ship)
    }
}