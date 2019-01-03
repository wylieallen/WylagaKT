package wylaga.control

import wylaga.input.Action
import wylaga.input.ActionMapper
import wylaga.model.entities.ships.Ship

class Controller(controlled: Ship) {
    private val pressController = ActionMapper()
    private val releaseController = ActionMapper()

    private val shipController = ShipController(controlled)

    private val inputController = DpadParser(shipController)

    init {
        pressController.bind(Action.UP) { inputController.upPressed = true }
        pressController.bind(Action.DOWN) { inputController.downPressed = true }
        pressController.bind(Action.LEFT) { inputController.leftPressed = true }
        pressController.bind(Action.RIGHT) { inputController.rightPressed = true }

        releaseController.bind(Action.UP) { inputController.upPressed = false }
        releaseController.bind(Action.DOWN) { inputController.downPressed = false }
        releaseController.bind(Action.LEFT) { inputController.leftPressed = false }
        releaseController.bind(Action.RIGHT) { inputController.rightPressed = false }

        pressController.bind(Action.PRIMARY) { inputController.firePressed = true }
        releaseController.bind(Action.PRIMARY) { inputController.firePressed = false }
    }

    fun press(action: Action) = pressController.execute(action)
    fun release(action: Action) = pressController.execute(action)
    fun update() = shipController.update()
}