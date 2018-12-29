package wylaga

import wylaga.control.Action
import wylaga.control.Controller
import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.tickables.Tickable
import wylaga.view.View
import wylaga.model.Model
import wylaga.model.ShipFactory
import wylaga.model.entities.Projectile
import wylaga.model.entities.Ship
import wylaga.util.DirectionVector
import wylaga.view.SpriteFactory
import wylaga.view.display.image.Base64Encoding


class Wylaga(decodeBase64: (Base64Encoding) -> Displayable) : Displayable, Tickable {
    private val model = Model()
    private val view = View()
    private val controller: Controller

    val press: (Action) -> Unit
    val release: (Action) -> Unit

    init {
        // Initialize display tree:
        view.addToBackground(SolidRect(1600.0, 900.0, Color(0, 0, 0)))

        // Wire listeners:
        model.subscribeShipSpawn(view::spawnSprite)
        model.subscribeProjectileSpawn(view::spawnChildSprite)

        model.subscribeShipDespawn(view::explodeSprite)
        model.subscribeProjectileDespawn(view::explodeSprite)

        val onDeath = model::flagForExpiration
        val onImpact = {projectile: Projectile, ship: Ship -> ship.damage(10.0); onDeath(projectile); }
        val onFire = {ship: Ship -> model.spawnProjectile(Projectile(ship.x + (ship.width / 2), ship.y - 10.0, 5.0, 5.0, DirectionVector(0.0, -1.0), 6.0, onImpact, model::despawnProjectile), ship)}

        val shipFactory = ShipFactory(onDeath = onDeath, onExpire = model::despawnShip, onFire = onFire)
        val spriteFactory = SpriteFactory(decodeBase64, view::despawnSprite)

        // Initialize player and controller:
        val player = shipFactory.makePlayer()
        view.setSprite(player, spriteFactory.makePlayer(player))
        model.spawnShip(player)

        this.controller = Controller(player)

        this.press = controller.press
        this.release = controller.release

        // Enemy:
        val enemy = shipFactory.makeEnemy()
        enemy.trajectory = DirectionVector.EAST
        view.setSprite(enemy, spriteFactory.makeEnemy(enemy))
        model.spawnShip(enemy)
    }

    override fun display(p: Painter) = view.display(p)

    override fun tick() {
        model.tick()
        view.tick()
        controller.tick()
    }
}