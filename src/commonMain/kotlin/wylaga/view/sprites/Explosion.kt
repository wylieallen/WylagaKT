package wylaga.view.sprites

import wylaga.util.DirectionVector
import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.tickables.CompositeTickable
import wylaga.view.display.tickables.Tickable
import kotlin.random.Random

class Explosion(x: Double, y: Double, radius: Double, particleCount: Int, particleColor: Color, private val onExpire: (Explosion) -> Unit) : Displayable,
    Tickable {
    private val displayRoot = CompositeDisplayable()
    private val tickRoot = CompositeTickable()

    private val expiredParticles = mutableSetOf<Particle>()

    init {
        for (i in 1..particleCount) {
            val speed = 4 + (Random.nextDouble() * 12)
            val trajectory = DirectionVector((Random.nextDouble() * 720) - 360, (Random.nextDouble() * 720) - 360)
            val lifespan = (radius / speed).toInt()
            val particle = Particle(x, y, trajectory.dx * speed, trajectory.dy * speed, lifespan, particleColor) { particle: Particle -> expireParticle(particle) }
            displayRoot.add(particle)
            tickRoot.add(particle)
        }
    }

    private fun expireParticle(particle: Particle) = expiredParticles.add(particle)

    override fun display(p: Painter) = displayRoot.display(p)
    override fun tick() {
        tickRoot.tick()
        expiredParticles.forEach { displayRoot.remove(it); tickRoot.remove(it); }
        expiredParticles.clear()
        if(displayRoot.size() <= 0) {
            onExpire(this)
        }
    }

    private class Particle(x: Double, y: Double, private val dx: Double, private val dy: Double, private var lifetime: Int, color: Color, private val onExpire: (Particle) -> Unit) : Displayable,
        Tickable {
        private val displayable = TranslatedDisplayable(x, y, SolidRect(2.0, 2.0, color))

        override fun display(p: Painter) = displayable.display(p)

        override fun tick() {
            displayable.x += dx
            displayable.y += dy
            lifetime--
            if(lifetime <= 0) {
                onExpire(this)
            }
        }
    }
}