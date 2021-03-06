package wylaga.stages

import wylaga.model.entities.ships.ShipFactory
import wylaga.model.entities.weapons.WeaponFactory
import wylaga.model.entities.Entity
import wylaga.model.entities.pilots.MirrorPilot
import wylaga.model.entities.pilots.RallyPilot
import wylaga.model.entities.pilots.RandomPilot
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.Weapon
import wylaga.view.SpriteFactory
import wylaga.view.sprites.Sprite

class StageFactory(private val weaponFactory: WeaponFactory, private val shipFactory: ShipFactory, private val spriteFactory: SpriteFactory, private val wingmanMap: MutableMap<Ship, Pair<Ship, Ship>>) {

    fun stage1(onStageComplete: () -> Unit) : Stage {
        val pair = makeWing(800.0, 125.0)
        return SimpleStage(pair.first, pair.second, onStageComplete)
    }

    fun stage2(onStageComplete: () -> Unit) : Stage {
        val spritePairs = mutableSetOf<Pair<Ship, Sprite>>()
        val weaponPairs = mutableSetOf<Pair<Weapon, (Entity) -> Sprite>>()

        var pair = makeWing(800.0, 125.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        pair = makeWing(600.0, 100.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        pair = makeWing(1000.0, 100.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        return SimpleStage(spritePairs, weaponPairs, onStageComplete)
    }

    fun stage3(onStageComplete: () -> Unit) : Stage {
        val spritePairs = mutableSetOf<Pair<Ship, Sprite>>()
        val weaponPairs = mutableSetOf<Pair<Weapon, (Entity) -> Sprite>>()

        var pair = makeWing(800.0, 125.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        pair = makeWing(600.0, 100.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        pair = makeWing(1000.0, 100.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        pair = makeWing(400.0, 100.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        pair = makeWing(1200.0, 100.0)

        spritePairs.addAll(pair.first)
        weaponPairs.addAll(pair.second)

        return SimpleStage(spritePairs, weaponPairs, onStageComplete)
    }

    fun stage4(onStageComplete: () -> Unit) : Stage {
        val pair = makeBossWing(800.0, 125.0)
        return SimpleStage(pair.first, pair.second, onStageComplete)
    }

    private fun makeBossWing(x: Double, y: Double): Pair<Set<Pair<Ship, Sprite>>, Set<Pair<Weapon, (Entity) -> Sprite>>> {
        val spritePairs = mutableSetOf<Pair<Ship, Sprite>>()
        val weaponPairs = mutableSetOf<Pair<Weapon, (Entity) -> Sprite>>()

        val pairs = makeNemesis(x - 25.0, y)
        spritePairs.add(pairs.first)
        weaponPairs.add(pairs.second)

        return Pair(spritePairs, weaponPairs)
    }

    private fun makeNemesis(x: Double, y: Double) : Pair<Pair<Ship, Sprite>, Pair<Weapon, (Entity) -> Sprite>> {
        val weapon = weaponFactory.makePlayerWeapon(50.0)
        val nemesis = shipFactory.makeEvilPlayer(x, y - 300, weapon = weapon, pilot = makeRallyPilot(x, y, 0.08))
        return Pair(Pair(nemesis, spriteFactory.makePlayer(nemesis)), Pair(weapon, spriteFactory::makeRedPlayerProjectile))
    }

    private fun makeWing(x: Double, y: Double): Pair<Set<Pair<Ship, Sprite>>, Set<Pair<Weapon, (Entity) -> Sprite>>> {
        val spritePairs = mutableSetOf<Pair<Ship, Sprite>>()
        val weaponPairs = mutableSetOf<Pair<Weapon, (Entity) -> Sprite>>()

        var pairs = makeBigEnemy(x - 25.0, y)
        spritePairs.add(pairs.first)
        weaponPairs.add(pairs.second)

        val leader = pairs.first.first

        pairs = makeSmallEnemy(x - 75, y - 50, leader)
        spritePairs.add(pairs.first)
        weaponPairs.add(pairs.second)

        val left = pairs.first.first

        pairs = makeSmallEnemy(x + 50, y - 50, leader)
        spritePairs.add(pairs.first)
        weaponPairs.add(pairs.second)

        val right = pairs.first.first

        wingmanMap[leader] = Pair(left, right)

        return Pair(spritePairs, weaponPairs)
    }

    private fun makeBigEnemy(x: Double, y: Double) : Pair<Pair<Ship, Sprite>, Pair<Weapon, (Entity) -> Sprite>> {
        val weapon = weaponFactory.makeEnemyWeapon(20.0)
        val enemy = shipFactory.makeEnemy(x, y - 300, weapon = weapon, pilot = makeRallyPilot(x, y))

        return Pair(Pair(enemy, spriteFactory.makeBigEnemy(enemy)), Pair(weapon, spriteFactory::makeGreenSquareProjectile))
    }

    private fun makeSmallEnemy(x: Double, y: Double, leader: Ship) : Pair<Pair<Ship, Sprite>, Pair<Weapon, (Entity) -> Sprite>> {
        val weapon = weaponFactory.makeEnemyWeapon(10.0)
        val enemy = shipFactory.makeSmallEnemy(x, y - 300, weapon, makeMirrorPilot(leader))

        return Pair(Pair(enemy, spriteFactory.makeEnemy(enemy)), Pair(weapon, spriteFactory::makeGreenSquareProjectile))
    }

    private fun makeRallyPilot(x: Double, y: Double, fireChance: Double = 0.02) = RallyPilot(x, y) { it.activePilot = RandomPilot(0.01, fireChance, 0.01) }
    private fun makeMirrorPilot(leader: Ship) = MirrorPilot(leader, 4)
}