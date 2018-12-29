package wylaga.view.display.displayables.decorators

import wylaga.view.display.Painter
import wylaga.view.display.displayables.AbstractDisplayable
import wylaga.view.display.displayables.Displayable
import kotlin.math.PI

const val TWO_PI = PI * 2

class RotatedDisplayable(private val target: Displayable,
                         private val centerX: Double,
                         private val centerY: Double,
                         private var theta: Double) : AbstractDisplayable() {

    override fun doDisplay(p: Painter) {
        p.translate(centerX, centerY)
        p.rotate(theta)
        p.translate(-centerX, -centerY)
        target.display(p)
    }

    fun rotate(radians: Double) {
        theta += radians
        if(theta > TWO_PI || theta < -TWO_PI) {
            theta %= TWO_PI
        }
    }
}