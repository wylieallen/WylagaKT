package wylaga.view.display.displayables.decorators

import wylaga.view.display.Painter
import wylaga.view.display.displayables.AbstractDisplayable
import wylaga.view.display.displayables.Displayable

class TranslatedDisplayable(var x: Double, var y: Double, var target: Displayable) : AbstractDisplayable() {
    override fun doDisplay(p: Painter) {
        p.translate(x, y)
        target.display(p)
    }
}
