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
import wylaga.model.entities.pickups.PickupFactory
import wylaga.model.entities.ships.ShipFactory
import wylaga.model.entities.weapons.WeaponFactory
import wylaga.model.entities.Entity
import wylaga.model.entities.pilots.ControlBufferPilot
import wylaga.model.entities.pilots.MirrorPilot
import wylaga.model.entities.pilots.RandomPilot
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.expiration.Cause
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
        model.subscribePickupSpawn(view::spawnSprite)

        model.subscribePlayerShipDespawn(view::explodeSprite)
        model.subscribeFriendlyShipDespawn(view::explodeSprite)
        model.subscribeFriendlyProjectileDespawn { projectile, cause -> if(cause === Cause.IMPACT) view.explodeSprite(projectile) else view.despawnSprite(projectile) }
        model.subscribeHostileShipDespawn(view::explodeSprite)
        model.subscribeHostileProjectileDespawn { projectile, cause -> if(cause === Cause.IMPACT) view.explodeSprite(projectile) else view.despawnSprite(projectile) }
        model.subscribePickupDespawn { pickup, cause -> if(cause === Cause.IMPACT) view.explodeSprite(pickup) else view.despawnSprite(pickup) }

        model.subscribePlayerShipDespawn { playerScore -= it.points }
        model.subscribeFriendlyShipDespawn { playerScore -= it.points }
        model.subscribeHostileShipDespawn { playerScore += it.points }


        val spriteFactory = SpriteFactory(decodeBase64, view::despawnSprite, view::getMuzzleFlash)

        val friendlyWeaponFactory = WeaponFactory { projectile, cause -> model.despawnFriendlyProjectile(projectile, cause) }

        val playerShipFactory = ShipFactory(onDeath = { model.despawnPlayerShip(it) }, spawnProjectile = model::spawnFriendlyProjectile, orientation = Entity.Orientation.NORTH)
        val friendlyShipFactory = ShipFactory({model.despawnFriendlyShip(it)}, model::spawnFriendlyProjectile, Entity.Orientation.NORTH)

        //initializeLifecycleDiagnosticWidget()

        // Initialize player and controller:
        val playerPilot = ControlBufferPilot()
        val playerWeapon = friendlyWeaponFactory.makePlayerWeapon(10.0)
        val player = playerShipFactory.makeHardpointedPlayer((WIDTH / 2.0) - (25.0), (3.0 * HEIGHT / 4.0), weapon = playerWeapon, pilot = playerPilot)
        view.setSprite(player, spriteFactory.makePlayer(player))
        view.setSpriteMaker(playerWeapon, spriteFactory::makeRedPlayerProjectile)
        model.spawnPlayerShip(player)

        val orangePlayerWeapon = friendlyWeaponFactory.makePlayerWeapon(14.0)
        view.setMuzzleFlash(orangePlayerWeapon, spriteFactory.makeOrangeMuzzleFlash())
        view.setSpriteMaker(orangePlayerWeapon, spriteFactory::makeOrangePlayerProjectile)

        val yellowPlayerWeapon = friendlyWeaponFactory.makePlayerWeapon(18.0)
        view.setMuzzleFlash(yellowPlayerWeapon, spriteFactory.makeYellowMuzzleFlash())
        view.setSpriteMaker(yellowPlayerWeapon, spriteFactory::makeYellowPlayerProjectile)

        val greenPlayerWeapon = friendlyWeaponFactory.makePlayerWeapon(22.0)
        view.setMuzzleFlash(greenPlayerWeapon, spriteFactory.makeGreenMuzzleFlash())
        view.setSpriteMaker(greenPlayerWeapon, spriteFactory::makeGreenPlayerProjectile)

        val cyanPlayerWeapon = friendlyWeaponFactory.makePlayerWeapon(26.0)
        view.setMuzzleFlash(cyanPlayerWeapon, spriteFactory.makeCyanMuzzleFlash())
        view.setSpriteMaker(cyanPlayerWeapon, spriteFactory::makeCyanPlayerProjectile)

        val magentaPlayerWeapon = friendlyWeaponFactory.makePlayerWeapon(30.0)
        view.setMuzzleFlash(magentaPlayerWeapon, spriteFactory.makeMagentaMuzzleFlash())
        view.setSpriteMaker(magentaPlayerWeapon, spriteFactory::makeMagentaPlayerProjectile)

        val playerWeaponUpgrades = arrayOf(orangePlayerWeapon, yellowPlayerWeapon, greenPlayerWeapon, cyanPlayerWeapon, magentaPlayerWeapon)

        val wingmanMap = mutableMapOf<Ship, Pair<Ship, Ship>>()

        fun spawnWingmen(ship: Ship) {
            val prev = wingmanMap[ship]
            if(prev != null)
            {
                prev.first.heal(prev.first.maxHealth)
                prev.second.heal(prev.second.maxHealth)
            } else {
                val leftWeapon = friendlyWeaponFactory.makeWingmanWeapon(8.0)
                view.setSpriteMaker(leftWeapon, spriteFactory::makeRedWingmanProjectile)
                val left = friendlyShipFactory.makeWingman(ship.x - 25, ship.y + 50, leftWeapon, MirrorPilot(ship, 5))
                view.setSprite(left, spriteFactory.makeWingman(left))

                val rightWeapon = friendlyWeaponFactory.makeWingmanWeapon(8.0)
                view.setSpriteMaker(rightWeapon, spriteFactory::makeRedWingmanProjectile)
                val right = friendlyShipFactory.makeWingman(ship.x + 50, ship.y + 50, rightWeapon, MirrorPilot(ship, 5))
                view.setSprite(right, spriteFactory.makeWingman(right))

                wingmanMap[ship] = Pair(left, right)
                model.spawnFriendlyShip(left)
                model.spawnFriendlyShip(right)
            }
        }

        fun spawnSuperWingmen(ship: Ship) {
            val prev = wingmanMap[ship]
            if(prev != null)
            {
                prev.first.damage(prev.first.maxHealth)
                prev.second.damage(prev.second.maxHealth)
            }

            val leftWeapon = friendlyWeaponFactory.makePlayerWeapon(10.0)
            view.setSpriteMaker(leftWeapon, spriteFactory::makeRedPlayerProjectile)
            val left = friendlyShipFactory.makeHardpointedPlayer(ship.x - 60, ship.y + 50, leftWeapon, MirrorPilot(ship, 5))
            view.setSprite(left, spriteFactory.makePlayer(left))

            val rightWeapon = friendlyWeaponFactory.makePlayerWeapon(10.0)
            view.setSpriteMaker(rightWeapon, spriteFactory::makeRedPlayerProjectile)
            val right = friendlyShipFactory.makeHardpointedPlayer(ship.x + 60, ship.y + 50, rightWeapon, MirrorPilot(ship, 5))
            view.setSprite(right, spriteFactory.makePlayer(right))

            wingmanMap[ship] = Pair(left, right)
            model.spawnFriendlyShip(left)
            model.spawnFriendlyShip(right)

            spawnWingmen(left)
            spawnWingmen(right)
        }

        val friendlyRandomPilot = RandomPilot(0.01, 0.02, 0.01, minY = 450.0, maxY = 850.0)
        val hostileRandomPilot = RandomPilot(0.01, 0.02, 0.01)

        fun handleFriendlyLeaderDespawn(leader: Ship) {
            val wingmen = wingmanMap.remove(leader)
            if(wingmen != null) {
                wingmen.first.activePilot = friendlyRandomPilot
                wingmen.second.activePilot = friendlyRandomPilot
            }
        }

        fun handleHostileLeaderDespawn(leader: Ship) {
            val wingmen = wingmanMap.remove(leader)
            if(wingmen != null) {
                wingmen.first.activePilot = hostileRandomPilot
                wingmen.second.activePilot = hostileRandomPilot
            }
        }

        model.subscribeFriendlyShipDespawn(::handleFriendlyLeaderDespawn)
        model.subscribePlayerShipDespawn(::handleFriendlyLeaderDespawn)
        model.subscribeHostileShipDespawn(::handleHostileLeaderDespawn)

        val pickupFactory = PickupFactory({ pickup, cause -> model.despawnPickup(pickup, cause) }, { playerScore += it }, playerWeaponUpgrades, ::spawnWingmen, ::spawnSuperWingmen)

        model.subscribeHostileShipDespawn {
            if(Random.nextDouble() <= 1) {
                val roll = Random.nextDouble()
                val x = it.x + (it.width / 2)
                val y = it.y + (it.height / 2)
                when {
                    roll <= 0.1 -> {
                        val pickup = pickupFactory.makeHealing(x, y)
                        view.setSprite(pickup, spriteFactory.makeHealingPickup(pickup))
                        model.spawnPickup(pickup)
                    }
                    roll <= 0.2 -> {
                        val pickup = pickupFactory.makeEnergy(x, y)
                        view.setSprite(pickup, spriteFactory.makeEnergyPickup(pickup))
                        model.spawnPickup(pickup)
                    }
                    roll <= 0.25 -> {
                        val pickup = pickupFactory.makeFullHealing(x, y)
                        view.setSprite(pickup, spriteFactory.makeFullHealingPickup(pickup))
                        model.spawnPickup(pickup)
                    }
                    roll <= 0.3 -> {
                        val pickup = pickupFactory.makeHealthUpgrade(x, y)
                        view.setSprite(pickup, spriteFactory.makeHealthUpgradePickup(pickup))
                        model.spawnPickup(pickup)
                    }
                    roll <= 0.6 -> {
                        val pickup = pickupFactory.makeSuperWingmen(x, y)
                        view.setSprite(pickup, spriteFactory.makeSuperWingmenPickup(pickup))
                        model.spawnPickup(pickup)
                    }
                    roll <= 0.7 -> {
                        val pickup = pickupFactory.makeWingmen(x, y)
                        view.setSprite(pickup, spriteFactory.makeWingmenPickup(pickup))
                        model.spawnPickup(pickup)
                    }
                    roll <= 0.8 -> {
                        val pickup = pickupFactory.makeWeaponUpgrade(x, y)
                        view.setSprite(pickup, spriteFactory.makeWeaponUpgradePickup(pickup))
                        model.spawnPickup(pickup)
                    }
                    else -> {
                        val pickup = pickupFactory.makePoints(x, y)
                        view.setSprite(pickup, spriteFactory.makePointsPickup(pickup))
                        model.spawnPickup(pickup)
                    }
                }
            }
        }

        val controllerFactory = ControllerFactory()
        this.controller = controllerFactory.makeCombatController(playerPilot)

        view.addToHud(TranslatedDisplayable(40.0, 40.0, StringDisplayable({"SHIELD: " + player.health.toInt() + "/" + player.maxHealth.toInt()}, "arial", 16, Color.WHITE)))
        view.addToHud(TranslatedDisplayable(40.0, 60.0, StringDisplayable({"ENERGY: " + player.energy.toInt() + "/" + player.maxEnergy.toInt()}, "arial", 16, Color.WHITE)))
        view.addToHud(TranslatedDisplayable(40.0, 80.0, StringDisplayable({"POINTS: $playerScore"}, "arial", 16, Color.WHITE)))

        val hostileWeaponFactory = WeaponFactory { projectile, cause -> model.despawnHostileProjectile(projectile, cause) }
        val hostileShipFactory = ShipFactory(onDeath = { model.despawnHostileShip(it) }, spawnProjectile = model::spawnHostileProjectile, orientation = Entity.Orientation.SOUTH)
        stageIterator = StageIterator(StageFactory(hostileWeaponFactory, hostileShipFactory, spriteFactory, wingmanMap), this::loadAndStartNextStage)

        loadAndStartNextStage()
    }

    private fun initializeLifecycleDiagnosticWidget() {
        var projectilesActive = 0
        var projectilesEver = 0
        var shipsActive = 0
        var shipsEver = 0
        var pickupsActive = 0
        var pickupsEver = 0

        model.subscribePlayerShipSpawn { shipsActive++; shipsEver++; }
        model.subscribeFriendlyShipSpawn { shipsActive++; shipsEver++; }
        model.subscribeHostileShipSpawn { shipsActive++; shipsEver++; }
        model.subscribeFriendlyProjectileSpawn { _, _ -> projectilesActive++; projectilesEver++; }
        model.subscribeHostileProjectileSpawn { _, _ -> projectilesActive++; projectilesEver++; }
        model.subscribePickupSpawn { pickupsActive++; pickupsEver++; }

        model.subscribePlayerShipDespawn { shipsActive-- }
        model.subscribeFriendlyShipDespawn { shipsActive-- }
        model.subscribeHostileShipDespawn { shipsActive-- }
        model.subscribeFriendlyProjectileDespawn { _, _ -> projectilesActive-- }
        model.subscribeHostileProjectileDespawn { _, _ -> projectilesActive-- }
        model.subscribePickupDespawn { _, _ -> pickupsActive-- }

        view.addToHud(TranslatedDisplayable(40.0, 800.0, StringDisplayable({ "SHIPS: $shipsActive/$shipsEver" }, "arial", 16, Color.WHITE)))
        view.addToHud(TranslatedDisplayable(40.0, 820.0, StringDisplayable({ "PROJS: $projectilesActive/$projectilesEver" }, "arial", 16, Color.WHITE)))
        view.addToHud(TranslatedDisplayable(40.0, 840.0, StringDisplayable({ "PKUPS: $pickupsActive/$pickupsEver" }, "arial", 16, Color.WHITE)))
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