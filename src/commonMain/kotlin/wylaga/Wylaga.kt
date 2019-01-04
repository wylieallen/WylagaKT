package wylaga

import wylaga.input.Action
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
import wylaga.model.entities.pilots.Pilot
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.SimpleWeapon
import wylaga.util.DirectionVector
import wylaga.view.SpriteFactory
import wylaga.view.display.image.Base64Encoding


class Wylaga(decodeBase64: (Base64Encoding) -> Displayable) : Displayable, Tickable {
    private val model = Model()
    private val view = View()
    private val controller: Controller

    fun press(action: Action) = controller.press(action)
    fun release(action: Action) = controller.release(action)

    init {
        // Initialize display tree:
        view.addToBackground(SolidRect(1600.0, 900.0, Color.BLACK))

        // Wire listeners:
        model.subscribeShipSpawn(view::spawnSprite)
        model.subscribeProjectileSpawn(view::spawnChildSprite)

        model.subscribeShipDespawn(view::explodeSprite)
        model.subscribeProjectileDespawn(view::explodeSprite)

        val onDeath = model::flagForExpiration
        val onImpact = {projectile: Projectile, ship: Ship -> ship.damage(10.0); onDeath(projectile); }

        val shipFactory = ShipFactory(onDeath = onDeath, onExpire = model::despawnShip, spawnProjectile = model::spawnProjectile)
        val spriteFactory = SpriteFactory(decodeBase64, view::despawnSprite)

        // Initialize player and controller:
        val playerPilot = Pilot()
        val playerWeapon = SimpleWeapon(4.0, 15.0, 6.0, onImpact, model::despawnProjectile)
        val player = shipFactory.makeHardpointedPlayer(weapon = playerWeapon, pilot = playerPilot)
        view.setSprite(player, spriteFactory.makePlayer(player))
        view.setSpriteMaker(playerWeapon, spriteFactory::makeRedPlayerProjectile)
        model.spawnShip(player)

        this.controller = Controller(playerPilot)

        // Enemy:
        val enemyPilot = Pilot()
        enemyPilot.trajectory = DirectionVector.EAST
        enemyPilot.wantsToFire = true
        val enemy = shipFactory.makeEnemy(weapon = playerWeapon, pilot = enemyPilot)
        view.setSprite(enemy, spriteFactory.makeEnemy(enemy))
        model.spawnShip(enemy)
    }

    override fun display(p: Painter) = view.display(p)

    override fun tick() {
        model.tick()
        view.tick()
    }
}