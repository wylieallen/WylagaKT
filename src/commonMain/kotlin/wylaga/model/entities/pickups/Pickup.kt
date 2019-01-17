package wylaga.model.entities.pickups

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.expiration.Cause
import wylaga.util.DirectionVector

class Pickup(x: Double, y: Double, width: Double, height: Double, trajectory: DirectionVector, velocity: Double, private val onDisable: (Pickup, Cause) -> Unit, val effect: (Ship) -> Unit)
    : Entity(x, y, width, height, Orientation.NORTH, trajectory, velocity) {

    fun disable(cause: Cause) = onDisable(this, cause)
}