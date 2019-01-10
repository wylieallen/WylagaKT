package wylaga.view.display.tickables

class SimpleTickable(private val onTick: () -> Unit) : Tickable {
    override fun tick() = onTick()
}