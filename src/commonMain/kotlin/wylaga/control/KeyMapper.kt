package wylaga.control

class KeyMapper {
    private val pressMap = mutableMapOf<Int, () -> Unit>()
    private val releaseMap = mutableMapOf<Int, () -> Unit>()

    fun bindPress(keyCode: Int, function: () -> Unit) = pressMap.put(keyCode, function)
    fun bindRelease(keyCode: Int, function: () -> Unit) = releaseMap.put(keyCode, function)

    fun keyDown(keyCode: Int) {
        pressMap.getOrElse(keyCode){ {} }()
    }

    fun keyUp(keyCode: Int) {
        releaseMap.getOrElse(keyCode){ {} }()
    }
}