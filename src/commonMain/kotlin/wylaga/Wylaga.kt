package wylaga

import wylaga.external.Action
import wylaga.control.Controller
import wylaga.control.ControllerFactory
import wylaga.view.display.Color
import wylaga.view.display.Painter
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.tickables.Tickable
import wylaga.view.View
import wylaga.model.Model
import wylaga.model.ShipFactory
import wylaga.model.WeaponFactory
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.pilots.ControlBufferPilot
import wylaga.model.entities.ships.Ship
import wylaga.stages.StageFactory
import wylaga.stages.StageIterator
import wylaga.view.SpriteFactory
import wylaga.view.backgrounds.Starfield
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.displayables.primitives.StringDisplayable
import wylaga.view.display.image.Base64Encoding

const val WIDTH = 1600
const val HEIGHT = 900

class Wylaga(decodeBase64: (Base64Encoding) -> Displayable) : Displayable, Tickable {
    private val model = Model()
    private val view = View()
    private var controller: Controller

    private val stageIterator: StageIterator

    init {
        // Initialize background:
        //view.addToBackground(SolidRect(1600.0, 900.0, Color.BLACK))
        val starfield = Starfield(WIDTH.toDouble(), HEIGHT.toDouble(), 200)
        view.addToBackground(starfield)
        view.addTickable(starfield)


        // Wire listeners:
        model.subscribeFriendlyShipSpawn(view::spawnSprite)
        model.subscribeHostileShipSpawn(view::spawnSprite)
        model.subscribeFriendlyProjectileSpawn(view::spawnChildSprite)
        model.subscribeHostileProjectileSpawn(view::spawnChildSprite)

        model.subscribeFriendlyShipDespawn(view::explodeSprite)
        model.subscribeFriendlyProjectileDespawn(view::explodeSprite)
        model.subscribeHostileShipDespawn(view::explodeSprite)
        model.subscribeHostileProjectileDespawn(view::explodeSprite)

        val friendlyWeaponFactory = WeaponFactory(onProjectileDespawn = model::despawnFriendlyProjectile, onProjectileDisable = model::flagForExpiration)
        val friendlyShipFactory = ShipFactory(onDeath = model::flagForExpiration, onExpire = model::despawnFriendlyShip, spawnProjectile = model::spawnFriendlyProjectile)
        val spriteFactory = SpriteFactory(decodeBase64, view::despawnSprite)

        // Initialize player and controller:
        val playerPilot = ControlBufferPilot()
        val playerWeapon = friendlyWeaponFactory.makePlayerWeapon(10.0)
        val player = friendlyShipFactory.makeHardpointedPlayer((WIDTH / 2.0) - (25.0), (3.0 * HEIGHT / 4.0), weapon = playerWeapon, pilot = playerPilot)
        view.setSprite(player, spriteFactory.makePlayer(player))
        view.setSpriteMaker(playerWeapon, spriteFactory::makeRedPlayerProjectile)
        model.spawnFriendlyShip(player)

        val controllerFactory = ControllerFactory()
        this.controller = controllerFactory.makeCombatController(playerPilot)

        view.addToHud(TranslatedDisplayable(40.0, 40.0, StringDisplayable({"SHIELD: " + player.health.toInt() + "/" + player.maxHealth.toInt()}, "arial", 16, Color.WHITE)))
        view.addToHud(TranslatedDisplayable(40.0, 60.0, StringDisplayable({"ENERGY: " + player.energy.toInt() + "/" + player.maxEnergy.toInt()}, "arial", 16, Color.WHITE)))

        val hostileWeaponFactory = WeaponFactory(onProjectileDespawn = model::despawnHostileProjectile, onProjectileDisable = model::flagForExpiration)
        val hostileShipFactory = ShipFactory(onDeath = model::flagForExpiration, onExpire = model::despawnHostileShip, spawnProjectile = model::spawnHostileProjectile)
        stageIterator = StageIterator(StageFactory(hostileWeaponFactory, hostileShipFactory, spriteFactory), this::loadAndStartNextStage)

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