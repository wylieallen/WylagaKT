package wylaga.view

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.view.display.Color
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.image.Base64Encoding
import wylaga.view.sprites.Sprite

class SpriteFactory(decodeBase64: (Base64Encoding) -> Displayable, private val onExpire: (Sprite) -> Unit) {
    private val imageLoader = ImageLoader(decodeBase64)

    fun makeEnemy(enemy: Ship) : Sprite {
        return Sprite(enemy, imageLoader.enemy, onExpire, Color.GREEN, 100, 80.0)
    }

    fun makePlayer(player: Ship) : Sprite {
//        val displayable2 = CompositeDisplayable()
//        displayable2.add(SolidRect(player.width, player.height, Color.GREEN))
//        val rotatedDisplayable = RotatedDisplayable(SolidRect(player.width, player.height, Color.RED.withAlpha(0x7F)), player.width / 2, player.height / 2, PI / 4)
//        displayable2.add(rotatedDisplayable)
//        return Sprite(player, displayable2, onExpire, Color.MAGENTA, 70, 60.0, SimpleTickable{ rotatedDisplayable.rotate(0.04) })
        return Sprite(player, imageLoader.player, onExpire, Color.MAGENTA, 70, 60.0)
    }

    fun makeRedPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.redPlayerProjectile, onExpire, Color.RED, 70, 50.0)

    fun makeGreenSquareProjectile(projectile: Entity) = Sprite(projectile, imageLoader.greenSquareProjectile, onExpire, Color.GREEN, 70, 50.0)
}