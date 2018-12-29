package wylaga.model

import wylaga.model.entities.Ship

class ShipMaker(private val onDeath: (Ship) -> Unit, private val onExpire: (Ship) -> Unit, private val onFire: (Ship) -> Unit) {
    fun makeEnemy(x: Double = 256.0, y: Double = 256.0) : Ship {
        return Ship(x, y, 50.0, 50.0,2.0, 100.0, onDeath, onExpire, onFire)
    }

    fun makePlayer(x: Double = 512.0, y: Double = 256.0) : Ship {
        return Ship(x, y, 50.0, 50.0, 2.0,100.0, onDeath, onExpire, onFire)
    }
}