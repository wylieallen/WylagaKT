package wylaga.input

class CombatActionHandler(dpad: DpadParser) : ActionHandler {
    private val pressMap = ActionMapper()
    private val releaseMap = ActionMapper()

    override fun press(action: Action) = pressMap.execute(action)
    override fun release(action: Action) = releaseMap.execute(action)

    init {
        pressMap.bind(Action.UP) { dpad.upPressed = true }
        pressMap.bind(Action.DOWN) { dpad.downPressed = true }
        pressMap.bind(Action.LEFT) { dpad.leftPressed = true }
        pressMap.bind(Action.RIGHT) { dpad.rightPressed = true }

        releaseMap.bind(Action.UP) { dpad.upPressed = false }
        releaseMap.bind(Action.DOWN) { dpad.downPressed = false }
        releaseMap.bind(Action.LEFT) { dpad.leftPressed = false }
        releaseMap.bind(Action.RIGHT) { dpad.rightPressed = false }

        pressMap.bind(Action.PRIMARY) { dpad.firePressed = true }
        releaseMap.bind(Action.PRIMARY) { dpad.firePressed = false }
    }
}