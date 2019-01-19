package wylaga.view.display.displayables.primitives

import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable

class NullDisplayable private constructor() : Displayable {
    override fun display(p: Painter) {}

    companion object {
        val INSTANCE = NullDisplayable()
    }
}