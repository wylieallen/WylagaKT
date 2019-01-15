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
import wylaga.model.PickupFactory
import wylaga.model.ShipFactory
import wylaga.model.WeaponFactory
import wylaga.model.entities.Entity
import wylaga.model.entities.pickups.Pickup
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
import kotlin.random.Random

const val WIDTH = 1600.0
const val HEIGHT = 900.0

class Wylaga(decodeBase64: (Base64Encoding) -> Displayable) : Displayable, Tickable {
    private val model = Model()
    private val view = View()
    private var controller: Controller

    private val stageIterator: StageIterator
    private var playerScore = 0

    init {
        // Initialize background:
        val starfield = Starfield(WIDTH, HEIGHT, 200)
        view.addToBackground(starfield)
        view.addTickable(starfield)


        // Wire listeners:
        model.subscribePlayerShipSpawn(view::spawnSprite)
        model.subscribeFriendlyShipSpawn(view::spawnSprite)
        model.subscribeHostileShipSpawn(view::spawnSprite)
        model.subscribeFriendlyProjectileSpawn(view::spawnChildSprite)
        model.subscribeHostileProjectileSpawn(view::spawnChildSprite)
        model.subscribePickupSpawn { view.spawnChildSprite(it, it.effect) }

        model.subscribePlayerShipDespawn(view::explodeSprite)
        model.subscribeFriendlyShipDespawn(view::explodeSprite)
        model.subscribeFriendlyProjectileDespawn(view::explodeSprite)
        model.subscribeHostileShipDespawn(view::explodeSprite)
        model.subscribeHostileProjectileDespawn(view::explodeSprite)
        model.subscribePickupDespawn(view::explodeSprite)

        model.subscribePlayerShipDespawn { playerScore -= it.points }
        model.subscribeFriendlyShipDespawn { playerScore -= it.points }
        model.subscribeHostileShipDespawn { playerScore += it.points }


        val pickupFactory = PickupFactory {model.despawnPickup(it)}

        model.subscribeHostileShipDespawn { if(Random.nextDouble() <= 1) { model.spawnPickup(pickupFactory.random(it.x + (it.width / 2), it.y + (it.height / 2))) }}

        val friendlyWeaponFactory = WeaponFactory {model.despawnFriendlyProjectile(it)}
        val playerShipFactory = ShipFactory(onDeath = {model.despawnPlayerShip(it)}, spawnProjectile = model::spawnFriendlyProjectile, orientation = Entity.Orientation.NORTH)
        val spriteFactory = SpriteFactory(decodeBase64, view::despawnSprite)

        // Initialize pickup sprites:
        view.setSpriteMaker(Pickup.Effect.HEALING, spriteFactory::makeHealingPickup)
        view.setSpriteMaker(Pickup.Effect.ENERGY, spriteFactory::makeEnergyPickup)

        // Initialize player and controller:
        val playerPilot = ControlBufferPilot()
        val playerWeapon = friendlyWeaponFactory.makePlayerWeapon(10.0)
        val player = playerShipFactory.makeHardpointedPlayer((WIDTH / 2.0) - (25.0), (3.0 * HEIGHT / 4.0), weapon = playerWeapon, pilot = playerPilot)
        view.setSprite(player, spriteFactory.makePlayer(player))
        view.setSpriteMaker(playerWeapon, spriteFactory::makeRedPlayerProjectile)
        model.spawnPlayerShip(player)

        val controllerFactory = ControllerFactory()
        this.controller = controllerFactory.makeCombatController(playerPilot)

        view.addToHud(TranslatedDisplayable(40.0, 40.0, StringDisplayable({"SHIELD: " + player.health.toInt() + "/" + player.maxHealth.toInt()}, "arial", 16, Color.WHITE)))
        view.addToHud(TranslatedDisplayable(40.0, 60.0, StringDisplayable({"ENERGY: " + player.energy.toInt() + "/" + player.maxEnergy.toInt()}, "arial", 16, Color.WHITE)))
        view.addToHud(TranslatedDisplayable(40.0, 80.0, StringDisplayable({"POINTS: $playerScore"}, "arial", 16, Color.WHITE)))

        val hostileWeaponFactory = WeaponFactory {model.despawnHostileProjectile(it)}
        val hostileShipFactory = ShipFactory(onDeath = {model.despawnHostileShip(it)}, spawnProjectile = model::spawnHostileProjectile, orientation = Entity.Orientation.SOUTH)
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