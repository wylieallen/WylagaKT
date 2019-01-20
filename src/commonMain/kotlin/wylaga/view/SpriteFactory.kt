package wylaga.view

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.Weapon
import wylaga.view.display.Color
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.displayables.primitives.SolidRect
import wylaga.view.display.image.Base64Encoding
import wylaga.view.display.tickables.*
import wylaga.view.display.tickables.primitives.IntervalTickable
import wylaga.view.sprites.Sprite

class SpriteFactory(decodeBase64: (Base64Encoding) -> Displayable, private val onExpire: (Sprite) -> Unit, private val getMuzzleFlash: (Weapon) -> Displayable) {
    private val imageLoader = ImageLoader(decodeBase64)

    fun makeWingman(wingman: Ship) : Sprite {
        val chassis = makeStandardShipChassis(wingman, 0.0, 0.0, imageLoader.wingmanBaseChassis, imageLoader.wingmanHurtChassis, imageLoader.wingmanDireChassis, imageLoader.wingmanHealChassis)
        val special = makeStandardShipSpecial(wingman, 5.0, 15.0, imageLoader.wingmanBaseSpecial, imageLoader.wingmanBoostSpecial)
        val weapon = makeStandardWeapon(wingman, 10.0, 0.0, imageLoader.wingmanBaseWeapon, imageLoader.wingmanFiringWeapon)
        val engine = makeStandardEngine(wingman, 5.0, 20.0, imageLoader.wingmanBaseEngine1, imageLoader.wingmanBaseEngine2, imageLoader.wingmanBrakeEngine, imageLoader.wingmanBoostEngine1, imageLoader.wingmanBoostEngine2)
        return Sprite(wingman, CompositeDisplayable(chassis.first, special.first, weapon.first, engine.first), onExpire, Color.MAGENTA, 100, 80.0,
            CompositeTickable(chassis.second, special.second, weapon.second, engine.second))
    }

    fun makeEnemy(enemy: Ship) : Sprite {
        val chassis = makeStandardShipChassis(enemy, 0.0, 0.0, imageLoader.enemyBaseChassis, imageLoader.enemyHurtChassis, imageLoader.enemyDireChassis, imageLoader.enemyHealChassis)
        val special = makeStandardShipSpecial(enemy, 3.0, 13.0, imageLoader.enemyBaseSpecial, imageLoader.enemyBoostSpecial)
        val weapon = makeStandardWeapon(enemy, 6.0, 1.0, imageLoader.enemyBaseWeapon, imageLoader.enemyFiringWeapon)
        val engine = makeStandardEngine(enemy, 7.0, 22.0, imageLoader.enemyBaseEngine, imageLoader.enemyBoostEngine, imageLoader.enemyBaseEngine, imageLoader.enemyBoostEngine, imageLoader.enemyBoostEngine)
        return Sprite(enemy, CompositeDisplayable(chassis.first, special.first, weapon.first, engine.first), onExpire, Color.GREEN, 100, 80.0,
            CompositeTickable(chassis.second, special.second, weapon.second, engine.second))
    }

    fun makeBigEnemy(enemy: Ship) : Sprite {
        val chassis = makeStandardShipChassis(enemy, 0.0, 0.0, imageLoader.bigEnemyBaseChassis, imageLoader.bigEnemyHurtChassis, imageLoader.bigEnemyDireChassis, imageLoader.bigEnemyHealChassis)
        val special = makeStandardShipSpecial(enemy, 0.0, 0.0, imageLoader.bigEnemyBaseSpecial, imageLoader.bigEnemyBoostSpecial)
        val weapon = makeStandardWeapon(enemy, 0.0, 0.0, imageLoader.bigEnemyBaseWeapon, imageLoader.bigEnemyFiringWeapon)
        val engine = makeStandardEngine(enemy, 0.0, 0.0, imageLoader.bigEnemyBaseEngine1, imageLoader.bigEnemyBaseEngine2,
            imageLoader.bigEnemyBrakeEngine, imageLoader.bigEnemyBoostEngine1, imageLoader.bigEnemyBoostEngine2)
        return Sprite(enemy, CompositeDisplayable(chassis.first, special.first, weapon.first, engine.first), onExpire, Color.GREEN, 100, 80.0,
            CompositeTickable(chassis.second, special.second, weapon.second, engine.second))
    }

    fun makePlayer(player: Ship) : Sprite {
        val chassis = makePlayerChassis(player)
        val special = makePlayerSpecial(player)
        val weapon = makePlayerWeapon(player)
        val engine = makePlayerEngine(player)

        return Sprite(player, CompositeDisplayable(chassis.first, special.first, weapon.first, engine.first), onExpire, Color.MAGENTA, 140, 100.0,
            CompositeTickable(chassis.second, special.second, weapon.second, engine.second))
    }

    private fun makePlayerEngine(player: Ship) : Pair<Displayable, Tickable> {
        val base1 = imageLoader.playerBaseEngine1
        val base2 = imageLoader.playerBaseEngine2

        val brake = imageLoader.playerBrakeEngine

        val boost1 = imageLoader.playerBoostEngine1
        val boost2 = imageLoader.playerBoostEngine2

        return makeStandardEngine(player, 12.0, 35.0, base1, base2, brake, boost1, boost2)
    }

    private fun makePlayerWeapon(player: Ship) : Pair<Displayable, Tickable> {
        val baseWeapon = imageLoader.playerBaseWeapon
        val firingWeapon = imageLoader.playerFiringWeapon

        return makeStandardWeapon(player, 22.0, 0.0, baseWeapon, firingWeapon)
    }

    private fun makePlayerSpecial(player: Ship) : Pair<Displayable, Tickable> {
        val baseSpecial = imageLoader.playerBaseSpecial
        val boostSpecial = imageLoader.playerBoostSpecial

        return makeStandardShipSpecial(player, 7.0, 24.0, baseSpecial, boostSpecial)
    }

    private fun makePlayerChassis(player: Ship) : Pair<Displayable, Tickable> {
        val baseChassis = imageLoader.playerBaseChassis
        val hurtChassis = imageLoader.playerHurtChassis
        val direChassis = imageLoader.playerDireChassis
        val healChassis = imageLoader.playerHealChassis

        return makeStandardShipChassis(player, 0.0, 0.0, baseChassis, hurtChassis, direChassis, healChassis)
    }

    private fun makeStandardEngine(ship: Ship, x: Double, y: Double, base1: Displayable, base2: Displayable, brake: Displayable, boost1: Displayable, boost2: Displayable) : Pair<Displayable, Tickable> {
        val engine = TranslatedDisplayable(x, y, base1)

        val baseTickable = IntervalTickable(40) { engine.target = if(engine.target === base1) base2 else base1 }
        val brakeTickable = SimpleTickable { engine.target = brake }
        val boostTickable = IntervalTickable(10) { engine.target = if(engine.target === boost1) boost2 else boost1 }
        val tickable = DelegateTickable(baseTickable)

        if(ship.orientation === Entity.Orientation.NORTH) {
            ship.subscribeTrajectory {
                val dy = it.trajectory.dy
                when {
                    dy < 0 -> {
                        tickable.delegate = boostTickable
                        boostTickable.skipAhead()
                    }
                    dy > 0 -> {
                        tickable.delegate = brakeTickable
                    }
                    else -> {
                        baseTickable.skipAhead()
                        tickable.delegate = baseTickable
                    }
                }
            }
        } else if(ship.orientation === Entity.Orientation.SOUTH) {
            ship.subscribeTrajectory {
                val dy = it.trajectory.dy
                when {
                    dy > 0 -> {
                        tickable.delegate = boostTickable
                        boostTickable.skipAhead()
                    }
                    dy < 0 -> {
                        tickable.delegate = brakeTickable
                    }
                    else -> {
                        baseTickable.skipAhead()
                        tickable.delegate = baseTickable
                    }
                }
            }
        }


        return Pair(engine, tickable)
    }

    private fun makeStandardWeapon(ship: Ship, x: Double, y: Double, base: Displayable, initialFiring: Displayable) : Pair<Displayable, Tickable> {
        val weapon = TranslatedDisplayable(x, y, base)
        val tickable = DelegateTickable()

        var firing = initialFiring

        ship.subscribeFire {
            weapon.target = firing
            tickable.delegate = CountdownTickable(3) {
                weapon.target = base
                tickable.delegate = NullTickable.instance
            }
        }

        ship.subscribeWeaponChange {
            firing = getMuzzleFlash(it)
        }

        return Pair(weapon, tickable)
    }

    private fun makeStandardShipSpecial(ship: Ship, x: Double, y: Double, base: Displayable, boost: Displayable) : Pair<Displayable, Tickable> {
        val special = TranslatedDisplayable(x, y, base)

        ship.subscribeBoost {
            special.target = if(it.wantsToBoost) {
                boost
            } else {
                base
            }
        }

        return Pair(special, NullTickable.instance)
    }

    private fun makeStandardShipChassis(ship: Ship, x: Double, y: Double, base: Displayable, hurt: Displayable, dire: Displayable, heal: Displayable) : Pair<Displayable, Tickable> {
        val chassis = TranslatedDisplayable(x, y, base)

        val chassisAnimation = DelegateTickable()

        ship.subscribeDamage {
            chassis.target = hurt
            chassisAnimation.delegate = CountdownTickable(6) {
                chassis.target = if(it.health <= (it.maxHealth * 0.2)) dire else base
                chassisAnimation.delegate = NullTickable.instance
            }
        }

        ship.subscribeHeal {
            chassis.target = heal
            chassisAnimation.delegate = CountdownTickable(6) {
                chassis.target = if(it.health <= (it.maxHealth * 0.2)) dire else base
                chassisAnimation.delegate = NullTickable.instance
            }
        }

        return Pair(chassis, chassisAnimation)
    }

    fun makeOrangeMuzzleFlash() = imageLoader.playerFiringOrangeWeapon
    fun makeYellowMuzzleFlash() = imageLoader.playerFiringYellowWeapon
    fun makeGreenMuzzleFlash() = imageLoader.playerFiringGreenWeapon
    fun makeCyanMuzzleFlash() = imageLoader.playerFiringCyanWeapon
    fun makeMagentaMuzzleFlash() = imageLoader.playerFiringMagentaWeapon

    fun makeRedWingmanProjectile(projectile: Entity) = Sprite(projectile, SolidRect(projectile.width, projectile.height, Color.RED), onExpire, Color.RED, 50, 40.0)
    fun makeRedPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.redPlayerProjectile, onExpire, Color.RED, 70, 50.0)
    fun makeOrangePlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.orangePlayerProjectile, onExpire, Color.ORANGE, 70, 50.0)
    fun makeYellowPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.yellowPlayerProjectile, onExpire, Color.YELLOW, 70, 50.0)
    fun makeGreenPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.greenPlayerProjectile, onExpire, Color.GREEN, 70, 50.0)
    fun makeCyanPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.cyanPlayerProjectile, onExpire, Color.CYAN, 70, 50.0)
    fun makeMagentaPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.magentaPlayerProjectile, onExpire, Color.MAGENTA, 70, 50.0)

    fun makeGreenSquareProjectile(projectile: Entity) = Sprite(projectile, imageLoader.greenSquareProjectile, onExpire, Color.GREEN, 70, 50.0)

    fun makeHealingPickup(pickup: Entity) = Sprite(pickup, imageLoader.healthPickup, onExpire, Color.CYAN, 50,  45.0)
    fun makeFullHealingPickup(pickup: Entity) = Sprite(pickup, imageLoader.fullHealthPickup, onExpire, Color.CYAN, 100, 60.0)
    fun makeEnergyPickup(pickup: Entity) = Sprite(pickup, imageLoader.energyPickup, onExpire, Color.YELLOW, 50, 45.0)
    fun makePointsPickup(pickup: Entity) = Sprite(pickup, imageLoader.pointsPickup, onExpire, Color.YELLOW, 50, 45.0)
    fun makeWeaponUpgradePickup(pickup: Entity) = Sprite(pickup, imageLoader.weaponUpgradePickup, onExpire, Color.MAGENTA, 100, 75.0)
    fun makeHealthUpgradePickup(pickup: Entity) = Sprite(pickup, imageLoader.healthUpgradePickup, onExpire, Color.CYAN, 100, 75.0)
    fun makeWingmenPickup(pickup: Entity) = Sprite(pickup, imageLoader.wingmenPickup, onExpire, Color.ORANGE, 100, 60.0)
    fun makeSuperWingmenPickup(pickup: Entity) = Sprite(pickup, imageLoader.superWingmenPickup, onExpire, Color.ORANGE, 200, 100.0)
}