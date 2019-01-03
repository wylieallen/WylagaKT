package wylaga.input

interface ActionHandler {
    fun press(action: Action)
    fun release(action: Action)
}