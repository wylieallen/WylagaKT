package wylaga.view.sprites

import wylaga.model.entities.Entity
import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.decorators.RotatedDisplayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.tickables.NullTickable
import wylaga.view.display.tickables.Tickable

class Sprite(private val entity: Entity,
             displayable: Displayable,
             private val onExpire: (Sprite) -> Unit,
             explosionColor: Color,
             explosionParticles: Int,
             explosionRadius: Double,
             private var tickable: Tickable = NullTickable.instance) : Displayable, Tickable {

    private val sprite = TranslatedDisplayable(entity.x, entity.y, RotatedDisplayable(displayable, entity.width / 2.0, entity.height / 2.0, entity.orientation.radians - Entity.Orientation.NORTH.radians))
    private val explosion = Explosion(entity.width / 2.0, entity.height / 2.0, explosionRadius, explosionParticles, explosionColor) {onExpire(this)}

    override fun display(p: Painter) = sprite.display(p)

    override fun tick() {
        sprite.x = entity.x
        sprite.y = entity.y
        tickable.tick()
    }

    fun explode() {
        sprite.target = explosion
        tickable = explosion
    }
}