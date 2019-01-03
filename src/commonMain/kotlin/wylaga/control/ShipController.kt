package wylaga.control

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector

class ShipController(private val ship: Ship) {
    var trajectory = DirectionVector(0.0, 0.0)
    var wantsToFire = false

    fun update() {
        ship.trajectory = trajectory
        ship.wantsToFire = wantsToFire
        wantsToFire = false // Todo: move semi-auto behavior from Controller to Model
    }
}