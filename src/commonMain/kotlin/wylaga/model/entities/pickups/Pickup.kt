package wylaga.model.entities.pickups

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.expiration.Cause
import wylaga.util.DirectionVector

class Pickup(x: Double, y: Double, width: Double, height: Double, trajectory: DirectionVector, velocity: Double, private val onDisable: (Pickup, Cause) -> Unit, val effect: Effect)
    : Entity(x, y, width, height, Orientation.NORTH, trajectory, velocity) {

    fun disable(cause: Cause) = onDisable(this, cause)

    enum class Effect(private val effect: (Ship) -> Unit) {
        HEALING({it.heal(it.maxHealth * 0.2)}), ENERGY({it.energy += (it.maxEnergy * 0.2)});

        operator fun invoke(ship: Ship) = effect(ship)
    }
}