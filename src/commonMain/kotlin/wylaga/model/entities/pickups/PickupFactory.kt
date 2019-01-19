package wylaga.model.entities.pickups

import wylaga.model.entities.ships.Ship
import wylaga.model.systems.expiration.Cause
import wylaga.util.DirectionVector

class PickupFactory(private val onDisable: (Pickup, Cause) -> Unit, private val addToScore: (Int) -> Unit) {
    fun makeHealing(x: Double, y: Double) = makePickup(x, y) { addToScore(20); it.heal(it.maxHealth * 0.2); }
    fun makeEnergy(x: Double, y: Double) = makePickup(x, y) { addToScore(20); it.energy = it.maxEnergy; }
    fun makePoints(x: Double, y: Double) = makePickup(x, y) { addToScore(100); }

    private fun makePickup(x: Double, y: Double, effect: (Ship) -> Unit) = Pickup(x - 7.5, y - 7.5, 15.0, 15.0, DirectionVector.SOUTH, 1.0, onDisable, effect)
}