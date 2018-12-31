package wylaga.control

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector

class ShipController(private val ship: Ship) {
    var trajectory = DirectionVector(0.0, 0.0)
    var wantsToFire = false

    fun tick() {
        ship.trajectory = trajectory
        ship.wantsToFire = wantsToFire
        wantsToFire = false
    }
}