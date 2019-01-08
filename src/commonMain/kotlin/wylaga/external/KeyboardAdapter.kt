package wylaga.external

import wylaga.Wylaga

class KeyboardAdapter(private val wylaga: Wylaga) {
    private val keyMap = KeyMapper()

    init {
        // Movement keys:
        bindKeyToAction(87, Action.UP)
        bindKeyToAction(65, Action.LEFT)
        bindKeyToAction(83, Action.DOWN)
        bindKeyToAction(68, Action.RIGHT)

        bindKeyToAction(38, Action.UP)
        bindKeyToAction(37, Action.LEFT)
        bindKeyToAction(40, Action.DOWN)
        bindKeyToAction(39, Action.RIGHT)

        // Action keys:
        bindKeyToAction(13, Action.PRIMARY) // todo: Enter isn't always keycode 13 on all platforms!!!
        bindKeyToAction(32, Action.PRIMARY)

        bindKeyToAction(16, Action.SECONDARY)
    }

    private fun bindKeyToAction(keyCode: Int, action: Action) {
        keyMap.bindPress(keyCode) {wylaga.press(action)}
        keyMap.bindRelease(keyCode) {wylaga.release(action)}
    }

    fun keyDown(keyCode: Int) { println("Keypressed $keyCode"); keyMap.keyDown(keyCode);}
    fun keyUp(keyCode: Int) = keyMap.keyUp(keyCode)

    private class KeyMapper {
        private val pressMap = mutableMapOf<Int, () -> Unit>()
        private val releaseMap = mutableMapOf<Int, () -> Unit>()

        fun bindPress(keyCode: Int, function: () -> Unit) = pressMap.put(keyCode, function)
        fun bindRelease(keyCode: Int, function: () -> Unit) = releaseMap.put(keyCode, function)

        fun keyDown(keyCode: Int) = pressMap.getOrElse(keyCode){ {} }()
        fun keyUp(keyCode: Int) = releaseMap.getOrElse(keyCode){ {} }()
    }
}