package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector
import kotlin.random.Random

class RandomPilot(private val redirectChance: Double, private val fireChance: Double) : Pilot {
    override fun update(ship: Ship) {
        if(Random.nextDouble() <= redirectChance) {
            ship.trajectory = DirectionVector.random()
        }

        ship.wantsToFire = Random.nextDouble() <= fireChance
    }
}