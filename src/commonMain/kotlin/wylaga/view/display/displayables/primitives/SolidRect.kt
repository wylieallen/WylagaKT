package wylaga.view.display.displayables.primitives

import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.AbstractDisplayable

class SolidRect(private val width: Double, private val height: Double, private val color: Color) : AbstractDisplayable() {
    override fun doDisplay(p: Painter) {
        p.setColor(color)
        p.fillRect(0.0, 0.0, width, height)
    }
}