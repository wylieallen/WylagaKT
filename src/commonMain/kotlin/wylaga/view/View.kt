package wylaga.view

import wylaga.model.entities.Entity
import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.tickables.CompositeTickable
import wylaga.view.display.tickables.Tickable
import wylaga.view.sprites.Sprite

class View : Displayable, Tickable {
    private val sprites = CompositeDisplayable()
    private val background = CompositeDisplayable()
    private val hud = CompositeDisplayable()

    private val root = CompositeDisplayable(background, sprites, hud)

    private val tickables = CompositeTickable()

    private val entityToSpriteMap = mutableMapOf<Entity, Sprite>()
    private val sourceToSpriteMakerMap = mutableMapOf<Any, (Entity) -> Sprite>()

    private val expiredSprites = mutableSetOf<Sprite>()

    override fun display(p: Painter) = root.display(p)
    override fun tick()  {
        tickables.tick()
        expiredSprites.forEach(this::removeSprite)
        expiredSprites.clear()
    }

    private fun addSprite(sprite: Sprite) {
        sprites.add(sprite)
        tickables.add(sprite)
    }

    private fun removeSprite(sprite: Sprite) {
        sprites.remove(sprite)
        tickables.remove(sprite)
    }

    fun addToBackground(displayable: Displayable) = background.add(displayable)
    fun addToHud(displayable: Displayable) = hud.add(displayable)

    fun spawnChildSprite(entity: Entity, source: Any) = addSprite(makeChildSprite(entity, source))
    private fun makeChildSprite(entity: Entity, source: Any): Sprite {
        val sprite= getSpriteMaker(source)(entity)
        entityToSpriteMap[entity] = sprite
        return sprite
    }
    private fun getSpriteMaker(source: Any): (Entity) -> Sprite = sourceToSpriteMakerMap.getOrPut(source) {return {entity: Entity -> makeDefaultSprite(entity)}}


    fun spawnSprite(entity: Entity) = addSprite(getSprite(entity))
    fun despawnSprite(sprite: Sprite) { expiredSprites.add(sprite) }

    fun explodeSprite(entity: Entity) = explodeSprite(getSprite(entity))
    fun explodeSprite(sprite: Sprite) = sprite.explode()

    fun setSpriteMaker(source: Any, spriteMaker: (Entity) -> Sprite) {
        sourceToSpriteMakerMap[source] = spriteMaker
    }

    private fun getSprite(entity: Entity) = entityToSpriteMap.getOrPut(entity) {makeDefaultSprite(entity)}
    fun setSprite(entity: Entity, sprite: Sprite) = entityToSpriteMap.set(entity, sprite)
    fun unloadSprite(entity: Entity) = entityToSpriteMap.remove(entity)

    private fun makeDefaultSprite(entity: Entity) = Sprite(entity, SolidRect(entity.width, entity.height, Color.CYAN),
            this::despawnSprite, Color.CYAN, 70, 75.0)
}