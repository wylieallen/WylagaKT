package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector
import kotlin.random.Random

class RandomPilot(private val redirectChance: Double, private val fireChance: Double, private val toggleBoostChance: Double,
                  private val minX: Double = 0.0, private val minY: Double = 0.0,
                  private val maxX: Double = 1550.0, private val maxY: Double = 400.0) : Pilot {
    override fun update(ship: Ship) {
        if(ship.x <= minX || ship.x >= maxX || ship.y <= minY || ship.y >= maxY) {
            val dx = when {
                ship.x <= minX -> 1.0
                ship.x >= maxX -> -1.0
                else -> Random.nextDouble() * 2 - 1
            }

            val dy = when {
                ship.y <= minY -> 1.0
                ship.y >= maxY -> -1.0
                else -> Random.nextDouble() * 2 - 1
            }

            ship.trajectory = DirectionVector(dx, dy)
        }
        else if(Random.nextDouble() <= redirectChance) {
            ship.trajectory = DirectionVector.random()
        }

        ship.wantsToFire = Random.nextDouble() <= fireChance

        if(Random.nextDouble() <= toggleBoostChance) {
            ship.wantsToBoost = !ship.wantsToBoost
        }
    }
}