package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector
import kotlin.math.sqrt

class RallyPilot(var targetX: Double, var targetY: Double, val onTargetReached: (Ship) -> Unit) : Pilot {
    override fun update(ship: Ship) {
        val dx = targetX - ship.x
        val dy = targetY - ship.y
        val distance = sqrt((dx * dx) + (dy * dy)) / ship.velocity
        when {
            distance <= 0.1 -> onTargetReached(ship)
            distance <= 1 -> ship.trajectory = DirectionVector(dx / ship.velocity, dy / ship.velocity)
            else -> ship.trajectory = DirectionVector(dx, dy)
        }
    }
}