package wylaga.stages

import wylaga.model.ShipFactory
import wylaga.model.WeaponFactory
import wylaga.model.entities.pilots.RandomPilot
import wylaga.view.SpriteFactory

class StageFactory(private val weaponFactory: WeaponFactory, private val shipFactory: ShipFactory, private val spriteFactory: SpriteFactory) {
    fun nextStage(onStageComplete: () -> Unit) : Stage {
        val pilot = RandomPilot(0.01, 0.02, 0.01)
        val weapon = weaponFactory.makeEnemyWeapon()
        val enemy = shipFactory.makeEnemy(800.0 - 25.0, 125.0, weapon = weapon, pilot = pilot)
        val smallWeapon = weaponFactory.makeEnemyWeapon()
        val leftEnemy = shipFactory.makeSmallEnemy(800.0 - 25.0 - 50.0, 75.0, weapon = smallWeapon, pilot = pilot)
        val spritePairs = mutableSetOf(Pair(enemy, spriteFactory.makeBigEnemy(enemy)), Pair(leftEnemy, spriteFactory.makeEnemy(leftEnemy)))
        val weaponPairs = mutableSetOf(Pair(weapon, spriteFactory::makeGreenSquareProjectile), Pair(smallWeapon, spriteFactory::makeGreenSquareProjectile))

        return SimpleStage(spritePairs, weaponPairs, onStageComplete)
    }
}