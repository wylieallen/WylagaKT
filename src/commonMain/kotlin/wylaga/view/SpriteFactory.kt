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

    fun makePlayer(player: Ship) : Sprite {
        val chassis = makePlayerChassis(player)
        val special = makePlayerSpecial(player)
        val weapon = makePlayerWeapon(player)
        val engine = makePlayerEngine(player)

        return Sprite(player, CompositeDisplayable(chassis.first, special.first, weapon.first, engine.first), onExpire, Color.MAGENTA, 70, 60.0,
            CompositeTickable(chassis.second, special.second, weapon.second, engine.second))
    }

    private fun makePlayerEngine(player: Ship) : Pair<Displayable, Tickable> {
        val baseEngine1 = imageLoader.playerBaseEngine1
        val baseEngine2 = imageLoader.playerBaseEngine2

        val brakeEngine = imageLoader.playerBrakeEngine

        val boostEngine1 = imageLoader.playerBoostEngine1
        val boostEngine2 = imageLoader.playerBoostEngine2

        val engine = TranslatedDisplayable(12.0, 35.0, baseEngine1)

        val baseTickable = IntervalTickable(40) { engine.target = if(engine.target === baseEngine1) baseEngine2 else baseEngine1 }
        val brakeTickable = SimpleTickable { engine.target = brakeEngine }
        val boostTickable = IntervalTickable(10) { engine.target = if(engine.target === boostEngine1) boostEngine2 else boostEngine1 }
        val tickable = DelegateTickable(baseTickable)

        player.subscribeTrajectory {
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

        return Pair(engine, tickable)
    }

    private fun makePlayerWeapon(player: Ship) : Pair<Displayable, Tickable> {
        val baseWeapon = imageLoader.playerBaseWeapon
        val firingWeapon = imageLoader.playerFiringWeapon

        val weapon = TranslatedDisplayable(22.0, 0.0, baseWeapon)
        val tickable = DelegateTickable()

        player.subscribeFire {
            weapon.target = firingWeapon
            tickable.delegate = CountdownTickable(3) {
                weapon.target = baseWeapon
                tickable.delegate = NullTickable.instance
            }
        }

        return Pair(weapon, tickable)
    }

    private fun makePlayerSpecial(player: Ship) : Pair<Displayable, Tickable> {
        val baseSpecial = imageLoader.playerBaseSpecial
        val boostSpecial = imageLoader.playerBoostSpecial

        val special = TranslatedDisplayable(7.0, 24.0, baseSpecial)

        player.subscribeBoost {
            special.target = if(it.wantsToBoost) {
                boostSpecial
            } else {
                baseSpecial
            }
        }

        return Pair(special, NullTickable.instance)
    }

    private fun makePlayerChassis(player: Ship) : Pair<Displayable, Tickable> {
        val baseChassis = imageLoader.playerBaseChassis
        val hurtChassis = imageLoader.playerHurtChassis
        val direChassis = imageLoader.playerDireChassis
        val healChassis = imageLoader.playerHealChassis

        val chassis = TranslatedDisplayable(0.0, 0.0, baseChassis)

        val chassisAnimation = DelegateTickable()

        player.subscribeDamage {
            chassis.target = hurtChassis
            chassisAnimation.delegate = CountdownTickable(6) {
                chassis.target = if(it.health <= 20) direChassis else baseChassis
                chassisAnimation.delegate = NullTickable.instance
            }
        }

//        player.subscribeHeal {
//            chassis.target = healChassis
//            chassisAnimation.delegate = CountdownTickable(6) {
//                chassis.target = if(it.health <= 20) direChassis else baseChassis
//                chassisAnimation.delegate = NullTickable.instance
//            }
//        }

        return Pair(chassis, chassisAnimation)
    }

    fun makeRedPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.redPlayerProjectile, onExpire, Color.RED, 70, 50.0)

    fun makeGreenSquareProjectile(projectile: Entity) = Sprite(projectile, imageLoader.greenSquareProjectile, onExpire, Color.GREEN, 70, 50.0)
}