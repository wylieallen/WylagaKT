package wylaga.view

import wylaga.model.entities.Ship
import wylaga.view.display.Color
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.decorators.RotatedDisplayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.image.Base64Encoding
import wylaga.view.display.tickables.SimpleTickable
import wylaga.view.sprites.Sprite
import kotlin.math.PI

class SpriteFactory(decodeBase64: (Base64Encoding) -> Displayable, private val onExpire: (Sprite) -> Unit) {
    private val imageLoader = ImageLoader(decodeBase64)

    fun makeEnemy(enemy: Ship) : Sprite {
        return Sprite(enemy, imageLoader.enemy, onExpire, Color(0xFF, 0x00, 0xFF), 70, 60.0)
    }

    fun makePlayer(player: Ship) : Sprite {
        val displayable2 = CompositeDisplayable()
        displayable2.add(SolidRect(player.width, player.height, Color(0x00, 0xFF, 0x00)))
        val rotatedDisplayable = RotatedDisplayable(SolidRect(player.width, player.height, Color(0xFF, 0x00, 0x00, 0x7F)), player.width / 2, player.height / 2, PI / 4)
        displayable2.add(rotatedDisplayable)
        return Sprite(player, displayable2, onExpire, Color(0xFF, 0x00, 0xFF), 70, 60.0, SimpleTickable{ rotatedDisplayable.rotate(0.04) })
    }
}