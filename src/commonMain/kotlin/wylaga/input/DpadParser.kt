package wylaga.input

import wylaga.control.ShipController
import wylaga.util.DirectionVector

class DpadParser(private val shipController: ShipController) {
    var downPressed = false
        set(bool) {
            field = bool
            updateTrajectory()
        }

    var upPressed = false
        set(bool) {
            field = bool
            updateTrajectory()
        }

    var leftPressed = false
        set(bool) {
            field = bool
            updateTrajectory()
        }

    var rightPressed = false
        set(bool) {
            field = bool
            updateTrajectory()
        }

    var firePressed = false
        set(bool) {
            field = bool
            shipController.wantsToFire = bool
        }

    private fun updateTrajectory() {
        val dx = when {
            leftPressed == rightPressed -> 0.0
            leftPressed -> -1.0
            else -> 1.0
        }

        val dy = when {
            downPressed == upPressed -> 0.0
            downPressed -> 1.0
            else -> -1.0
        }

        shipController.trajectory = DirectionVector(dx, dy)
    }
}