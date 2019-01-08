package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector

class ControlBufferPilot : Pilot {
    var trajectory = DirectionVector(0.0, 0.0)
    var wantsToFire = false
    var wantsToBoost = false

    override fun update(ship: Ship) {
        ship.trajectory = trajectory
        ship.wantsToBoost = wantsToBoost
        ship.wantsToFire = wantsToFire
        wantsToFire = false // Todo: move semi-auto behavior from Pilot to Weapon
    }
}