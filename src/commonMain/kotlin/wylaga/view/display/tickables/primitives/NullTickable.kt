package wylaga.view.display.tickables

class NullTickable private constructor() : Tickable {
    override fun tick() {}
    companion object {
        val instance = NullTickable()
    }
}