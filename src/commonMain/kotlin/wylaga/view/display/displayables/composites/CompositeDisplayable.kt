package wylaga.view.display.displayables.composites

import wylaga.view.display.Painter
import wylaga.view.display.displayables.AbstractDisplayable
import wylaga.view.display.displayables.Displayable

class CompositeDisplayable(vararg children: Displayable) : AbstractDisplayable() {
    private val children = linkedSetOf(*children)

    override fun doDisplay(p: Painter) = children.forEach { d -> d.display(p) }

    fun add(displayable: Displayable) = children.add(displayable)
    fun remove(displayable: Displayable) = children.remove(displayable)
    fun size() : Int { return children.size }
}