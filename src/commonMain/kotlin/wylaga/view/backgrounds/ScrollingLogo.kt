package wylaga.view.backgrounds

import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.tickables.Tickable

class ScrollingLogo(x: Double, y: Double, logo: Displayable, private val endY: Double, private val onEnd: (ScrollingLogo) -> Unit) : Displayable, Tickable {
    private val root = TranslatedDisplayable(x, y, logo)

    override fun display(p: Painter) = root.display(p)

    override fun tick() {
        root.y -= 2
        if(root.y <= endY) {
            onEnd(this)
        }
    }
}