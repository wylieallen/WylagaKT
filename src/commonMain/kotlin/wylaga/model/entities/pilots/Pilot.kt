package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship

interface Pilot {
    fun update(ship: Ship)
}