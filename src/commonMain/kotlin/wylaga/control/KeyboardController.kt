package wylaga.control

import wylaga.Wylaga

class KeyboardController(wylaga: Wylaga) {
    private val keyMap = KeyMapper()

    init {
        // Movement keys:
        keyMap.bindPress(87) {wylaga.press(Action.UP)}
        keyMap.bindPress(65) {wylaga.press(Action.LEFT)}
        keyMap.bindPress(83) {wylaga.press(Action.DOWN)}
        keyMap.bindPress(68) {wylaga.press(Action.RIGHT)}

        keyMap.bindRelease(87) {wylaga.release(Action.UP)}
        keyMap.bindRelease(65) {wylaga.release(Action.LEFT)}
        keyMap.bindRelease(83) {wylaga.release(Action.DOWN)}
        keyMap.bindRelease(68) {wylaga.release(Action.RIGHT)}

        keyMap.bindPress(38) {wylaga.press(Action.UP)}
        keyMap.bindPress(37) {wylaga.press(Action.LEFT)}
        keyMap.bindPress(40) {wylaga.press(Action.DOWN)}
        keyMap.bindPress(39) {wylaga.press(Action.RIGHT)}

        keyMap.bindRelease(38) {wylaga.release(Action.UP)}
        keyMap.bindRelease(37) {wylaga.release(Action.LEFT)}
        keyMap.bindRelease(40) {wylaga.release(Action.DOWN)}
        keyMap.bindRelease(39) {wylaga.release(Action.RIGHT)}

        // Action keys:
        keyMap.bindPress(13) { wylaga.press(Action.PRIMARY) }
        keyMap.bindPress(32) { wylaga.press(Action.PRIMARY) }

        keyMap.bindRelease(13) { wylaga.release(Action.PRIMARY) }
        keyMap.bindRelease(32) { wylaga.release(Action.PRIMARY) }
    }

    fun keyDown(keyCode: Int) { println("Keypressed $keyCode"); keyMap.keyDown(keyCode);}
    fun keyUp(keyCode: Int) = keyMap.keyUp(keyCode)
}