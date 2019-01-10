package wylaga.view.display.tickables

class CompositeTickable(vararg children: Tickable) : Tickable {
    private val children = mutableSetOf(*children)

    fun add(t: Tickable) = children.add(t)
    fun remove(t: Tickable) = children.remove(t)

    override fun tick() = children.forEach(Tickable::tick)
}