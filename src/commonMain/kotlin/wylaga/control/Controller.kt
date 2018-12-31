package wylaga.control

import wylaga.model.entities.ships.Ship

class Controller(controlled: Ship) {
    private val pressController = ActionMapper()
    private val releaseController = ActionMapper()

    private val entityController = ShipController(controlled)

    private val inputController = DpadController(entityController)

    val press: (Action) -> Unit
    val release: (Action) -> Unit


    init {
        press = pressController.execute
        release = releaseController.execute

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

    fun tick() {
        inputController.tick()
        entityController.tick()
    }
}