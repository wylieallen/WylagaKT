package wylaga.view

import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.view.display.Color
import wylaga.view.display.displayables.Displayable
import wylaga.view.display.displayables.composites.CompositeDisplayable
import wylaga.view.display.displayables.decorators.TranslatedDisplayable
import wylaga.view.display.image.Base64Encoding
import wylaga.view.display.tickables.*
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

        return Sprite(player, CompositeDisplayable(chassis.first, special.first, weapon.first), onExpire, Color.MAGENTA, 70, 60.0,
            CompositeTickable(chassis.second, special.second, weapon.second))
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
            special.target = if(player.wantsToBoost) {
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
                chassis.target = if(player.health <= 20) direChassis else baseChassis
                chassisAnimation.delegate = NullTickable.instance
            }
        }

//        player.subscribeHeal {
//            chassis.target = healChassis
//            chassisAnimation.delegate = CountdownTickable(6) {
//                chassis.target = if(player.health <= 20) direChassis else baseChassis
//                chassisAnimation.delegate = NullTickable.instance
//            }
//        }

        return Pair(chassis, chassisAnimation)
    }

    fun makeRedPlayerProjectile(projectile: Entity) = Sprite(projectile, imageLoader.redPlayerProjectile, onExpire, Color.RED, 70, 50.0)

    fun makeGreenSquareProjectile(projectile: Entity) = Sprite(projectile, imageLoader.greenSquareProjectile, onExpire, Color.GREEN, 70, 50.0)
}