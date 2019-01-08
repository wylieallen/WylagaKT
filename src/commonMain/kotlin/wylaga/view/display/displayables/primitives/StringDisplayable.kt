package wylaga.view.display.displayables.primitives

import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.AbstractDisplayable

class StringDisplayable(private val stringGenerator: () -> String,
                        private val typeface: String,
                        private val fontSize: Int,
                        private val fontColor: Color) : AbstractDisplayable() {
    override fun doDisplay(p: Painter) {
        p.setColor(fontColor)
        p.setFont(typeface, fontSize)
        p.drawString(stringGenerator())
    }
}