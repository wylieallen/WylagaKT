package wylaga

import wylaga.external.Action
import wylaga.control.Controller
import wylaga.control.ControllerFactory
import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.tickables.Tickable
import wylaga.view.View
import wylaga.model.Model
import wylaga.model.ShipFactory
import wylaga.model.WeaponFactory
import wylaga.model.entities.Projectile
import wylaga.model.entities.pilots.ControlBufferPilot
import wylaga.model.entities.ships.Ship
import wylaga.stages.StageFactory
import wylaga.stages.StageIterator
import wylaga.view.SpriteFactory
import wylaga.view.display.image.Base64Encoding


class Wylaga(decodeBase64: (Base64Encoding) -> Displayable) : Displayable, Tickable {
    private val model = Model()
    private val view = View()
    private var controller: Controller

    private val stageIterator: StageIterator

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

        val weaponFactory = WeaponFactory(onImpact = onImpact, onProjectileDespawn = model::despawnProjectile)
        val shipFactory = ShipFactory(onDeath = onDeath, onExpire = model::despawnShip, spawnProjectile = model::spawnProjectile)
        val spriteFactory = SpriteFactory(decodeBase64, view::despawnSprite)

        // Initialize player and controller:
        val playerPilot = ControlBufferPilot()
        val playerWeapon = weaponFactory.makePlayerWeapon()
        val player = shipFactory.makeHardpointedPlayer(weapon = playerWeapon, pilot = playerPilot)
        view.setSprite(player, spriteFactory.makePlayer(player))
        view.setSpriteMaker(playerWeapon, spriteFactory::makeRedPlayerProjectile)
        model.spawnShip(player)

        val controllerFactory = ControllerFactory()
        this.controller = controllerFactory.makeCombatController(playerPilot)

        val stageFactory = StageFactory(weaponFactory, shipFactory, spriteFactory)
        stageIterator = StageIterator(stageFactory, this::loadAndStartNextStage)

        loadAndStartNextStage()
    }

    private fun loadAndStartNextStage() {
        if(stageIterator.hasNext()) {
            val stage = stageIterator.next()
            stage.load(view)
            stage.start(model)
        } else {
            println("Game complete!")
        }
    }

    fun press(action: Action) = controller.press(action)
    fun release(action: Action) = controller.release(action)

    override fun display(p: Painter) = view.display(p)

    override fun tick() {
        model.tick()
        view.tick()
    }
}