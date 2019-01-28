package wylaga.view.backgrounds

import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.tickables.Tickable
import wylaga.view.display.tickables.composites.CompositeTickable
import kotlin.random.Random

const val LAYERS = 4

class Starfield(private val width: Double, private val height: Double, starCount: Int) : Displayable, Tickable {
    private val displayRoot = CompositeDisplayable()
    private val tickRoot = CompositeTickable()

    init {
        displayRoot.add(SolidRect(width, height, Color.BLACK))

        val starsPerLayer = starCount / LAYERS

        for(i in 1..LAYERS) {
            val topLayer = StarLayer(0.0, -height, i, starsPerLayer)
            val bottomLayer = StarLayer(0.0, 0.0, i, starsPerLayer)
            displayRoot.add(topLayer)
            displayRoot.add(bottomLayer)
            tickRoot.add(topLayer)
            tickRoot.add(bottomLayer)
        }
    }

    override fun display(p: Painter) = displayRoot.display(p)

    override fun tick() = tickRoot.tick()

    private inner class StarLayer(x: Double, y: Double, private val dy: Int, starCount: Int) : Displayable, Tickable {
        private val displayable: TranslatedDisplayable

        init {
            val composite = CompositeDisplayable()
            displayable = TranslatedDisplayable(x, y, composite)

            for(i in 1..starCount) {
                composite.add(Star(Random.nextDouble(width), Random.nextDouble(height), 1.0 + Random.nextInt(2), Color(Random.nextInt(0xFF), Random.nextInt(0xFF), Random.nextInt(0xFF))))
            }
        }

        override fun display(p: Painter) = displayable.display(p)

        override fun tick() {
            displayable.y += dy
            if(displayable.y >= height) {
                displayable.y = -height
            }
        }
    }

    private class Star(x: Double, y: Double, size: Double, color: Color) : Displayable {
        private val displayable = TranslatedDisplayable(x, y, SolidRect(size, size, color))

        override fun display(p: Painter) = displayable.display(p)
    }
}