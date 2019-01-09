package wylaga.view.backgrounds

import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.tickables.CompositeTickable
import wylaga.view.display.tickables.Tickable
import kotlin.random.Random

class Starfield(width: Double, private val height: Double, starCount: Int) : Displayable, Tickable {
    private val displayRoot = CompositeDisplayable()
    private val tickRoot = CompositeTickable()

    init {
        displayRoot.add(SolidRect(width, height, Color.BLACK))

        for(i in 1..starCount) {
            val star = Star(Random.nextDouble() * width, Random.nextDouble() * height, Random.nextDouble() * 3 + 0.2, 1.0, Color(Random.nextInt(0xFF), Random.nextInt(0xFF), Random.nextInt(0xFF)))
            displayRoot.add(star)
            tickRoot.add(star)
        }
    }

    override fun display(p: Painter) = displayRoot.display(p)

    override fun tick() = tickRoot.tick()

    private inner class Star(x: Double, y: Double, private val dy: Double, size: Double, color: Color) : Displayable, Tickable {
        private val displayable = TranslatedDisplayable(x, y, SolidRect(size, size, color))

        override fun display(p: Painter) = displayable.display(p)

        override fun tick() {
            displayable.y += dy
            if(displayable.y >= height) {
                displayable.y = 0.0
            }
        }
    }
}