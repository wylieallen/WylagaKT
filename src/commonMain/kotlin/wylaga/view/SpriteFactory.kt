package wylaga.view

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.view.display.Color
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.image.Base64Encoding
import wylaga.view.display.tickables.*
import wylaga.view.display.tickables.primitives.IntervalTickable
import wylaga.view.sprites.Sprite

class SpriteFactory(decodeBase64: (Base64Encoding) -> Displayable, private val onExpire: (Sprite) -> Unit) {
    private val imageLoader = ImageLoader(decodeBase64)

    fun makeEnemy(enemy: Ship) : Sprite {
        return Sprite(enemy, imageLoader.enemy, onExpire, Color.GREEN, 100, 80.0)
    }

    fun makeBigEnemy(enemy: Ship) : Sprite {
        val chassis = makeStandardShipChassis(enemy, 0.0, 0.0, imageLoader.bigEnemyBaseChassis, imageLoader.bigEnemyHurtChassis, imageLoader.bigEnemyDireChassis)
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

        return Sprite(player, CompositeDisplayable(chassis.first, special.first, weapon.first, engine.first), onExpire, Color.MAGENTA, 70, 60.0,
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

        return makeStandardShipChassis(player, 0.0, 0.0, baseChassis, hurtChassis, direChassis)
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

    private fun makeStandardWeapon(ship: Ship, x: Double, y: Double, base: Displayable, firing: Displayable) : Pair<Displayable, Tickable> {
        val weapon = TranslatedDisplayable(x, y, base)
        val tickable = DelegateTickable()

        ship.subscribeFire {
            weapon.target = firing
            tickable.delegate = CountdownTickable(3) {
                weapon.target = base
                tickable.delegate = NullTickable.instance
            }
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

    private fun makeStandardShipChassis(ship: Ship, x: Double, y: Double, base: Displayable, hurt: Displayable, dire: Displayable) : Pair<Displayable, Tickable> {
        val chassis = TranslatedDisplayable(x, y, base)

        val chassisAnimation = DelegateTickable()

        ship.subscribeDamage {
            chassis.target = hurt
            chassisAnimation.delegate = CountdownTickable(6) {
                chassis.target = if(it.health <= (it.maxHealth * 0.2)) dire else base
                chassisAnimation.delegate = NullTickable.instance
            }
        }

//        ship.subscribeHeal {
//            chassis.target = heal
//            chassisAnimation.delegate = CountdownTickable(6) {
//                chassis.target = if(it.health <= (it.maxHealth * 0.2)) dire else base
//                chassisAnimation.delegate = NullTickable.instance
//            }
//        }

        return Pair(chassis, chassisAnimation)
    }

    fun makeRedPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.redPlayerProjectile, onExpire, Color.RED, 70, 50.0)

    fun makeGreenSquareProjectile(projectile: Entity) = Sprite(projectile, imageLoader.greenSquareProjectile, onExpire, Color.GREEN, 70, 50.0)
}