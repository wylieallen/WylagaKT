package wylaga.view.display.tickables

class CompositeTickable : Tickable {
    private val children = mutableSetOf<Tickable>()

    fun add(t: Tickable) {
        children.add(t)
    }

    fun remove(t: Tickable) {
        children.remove(t)
    }

    override fun tick() {
        children.forEach { c -> c.tick() }
    }
}