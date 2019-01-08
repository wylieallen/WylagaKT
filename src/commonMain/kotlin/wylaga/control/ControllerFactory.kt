package wylaga.control

import wylaga.external.Action
import wylaga.external.DpadParser
import wylaga.model.entities.pilots.ControlBufferPilot

class ControllerFactory {
    fun makeCombatController(pilot: ControlBufferPilot): Controller {
        val controller = MappedController()
        val dpad = DpadParser(pilot)

        controller.setPress(Action.UP) { dpad.upPressed = true }
        controller.setPress(Action.DOWN) { dpad.downPressed = true }
        controller.setPress(Action.LEFT) { dpad.leftPressed = true }
        controller.setPress(Action.RIGHT) { dpad.rightPressed = true }

        controller.setRelease(Action.UP) { dpad.upPressed = false }
        controller.setRelease(Action.DOWN) { dpad.downPressed = false }
        controller.setRelease(Action.LEFT) { dpad.leftPressed = false }
        controller.setRelease(Action.RIGHT) { dpad.rightPressed = false }

        controller.setPress(Action.PRIMARY) { dpad.firePressed = true }
        controller.setRelease(Action.PRIMARY) { dpad.firePressed = false }

        controller.setPress(Action.SECONDARY) { dpad.specialPressed = true }
        controller.setRelease(Action.SECONDARY) { dpad.specialPressed = false }

        return controller
    }

    private class MappedController : Controller {
        private val pressMap = mutableMapOf<Action, () -> Unit>()
        private val releaseMap = mutableMapOf<Action, () -> Unit>()

        override fun press(action: Action) = pressMap.getOrElse(action){{}}()
        override fun release(action: Action) = releaseMap.getOrElse(action){{}}()

        fun setPress(action: Action, f: () -> Unit) = pressMap.set(action, f)
        fun setRelease(action: Action, f: () -> Unit) = releaseMap.set(action, f)
    }
}