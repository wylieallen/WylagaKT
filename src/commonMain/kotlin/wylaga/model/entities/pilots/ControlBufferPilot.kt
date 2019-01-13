package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector

class ControlBufferPilot : Pilot {
    var trajectory = DirectionVector(0.0, 0.0)
    var wantsToFire = false
        set(value) {
            field = value
            if(!value) {
                readyToFire = true
            }
        }

    private var readyToFire = true

    var wantsToBoost = false

    override fun update(ship: Ship) {
        ship.trajectory = trajectory
        ship.wantsToBoost = wantsToBoost
        ship.wantsToFire = (wantsToFire && readyToFire)
        if(wantsToFire) readyToFire = false // Todo: move semi-auto behavior from Pilot to Weapon
    }
}