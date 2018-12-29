package wylaga.control

import wylaga.util.DirectionVector

class DpadController(private val shipController: ShipController) {
    var downPressed = false
    var upPressed = false
    var leftPressed = false
    var rightPressed = false

    var firePressed = false
        set(bool) { // Todo: move semi-auto behavior from Controller to Model
            shipController.wantsToFire = bool
        }

    fun tick() {
        val dx = when {
            leftPressed == rightPressed -> {
                0.0
            }
            leftPressed -> {
                -1.0
            }
            else -> {
                1.0
            }
        }

        val dy = when {
            downPressed == upPressed -> {
                0.0
            }
            downPressed -> {
                1.0
            }
            else -> {
                -1.0
            }
        }

        shipController.trajectory = DirectionVector(dx, dy)

        // Todo: see above
//        shipController.wantsToFire = firePressed
    }
}