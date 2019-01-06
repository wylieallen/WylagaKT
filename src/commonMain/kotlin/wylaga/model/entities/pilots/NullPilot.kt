package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship

class NullPilot : Pilot {
    override fun update(ship: Ship) {}
}