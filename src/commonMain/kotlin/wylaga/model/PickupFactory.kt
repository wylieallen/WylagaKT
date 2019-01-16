package wylaga.model

import wylaga.model.entities.pickups.Pickup
import wylaga.model.systems.expiration.Cause
import wylaga.util.DirectionVector
import kotlin.random.Random

class PickupFactory(private val onDisable: (Pickup, Cause) -> Unit) {
    fun random(x: Double, y: Double) : Pickup {
        val roll = Random.nextDouble()
        return if(roll <= 0.33) {
            makePickup(x, y, Pickup.Effect.HEALING)
        } else {
            makePickup(x, y, Pickup.Effect.ENERGY)
        }
    }

    private fun makePickup(x: Double, y: Double, effect: Pickup.Effect) : Pickup = Pickup(x - 7.5, y - 7.5, 15.0, 15.0, DirectionVector.SOUTH, 1.0, onDisable, effect)
}