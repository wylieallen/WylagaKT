package wylaga.view.display.tickables.composites

import wylaga.view.display.tickables.Tickable

class CompositeTickable(vararg children: Tickable) : Tickable {
    private val children = mutableSetOf(*children)
    private val expired = mutableSetOf<Tickable>()

    fun add(t: Tickable) = children.add(t)
    fun remove(t: Tickable) = expired.add(t)

    override fun tick() {
        if(expired.size > 0)
        {
            children.removeAll(expired)
            expired.clear()
        }
        children.forEach(Tickable::tick)
    }
}