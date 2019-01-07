package wylaga.stages

import wylaga.model.Model
import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.Weapon
import wylaga.view.View
import wylaga.view.sprites.Sprite

class SimpleStage(private val spritePairs: Set<Pair<Ship, Sprite>>, private val weaponPairs: Set<Pair<Weapon, (Entity) -> Sprite>>,
                  private var onStageComplete: () -> Unit) : Stage {
    private var shipsLeft = 0

    override fun load(view: View) {
        spritePairs.forEach { view.setSprite(it.first, it.second) }
        weaponPairs.forEach { view.setSpriteMaker(it.first, it.second) }
    }

    override fun start(model: Model) {
        spritePairs.forEach { model.spawnShip(it.first) }
        shipsLeft = spritePairs.size

        val prevOnStageComplete = onStageComplete

        onStageComplete = {
            prevOnStageComplete()
            model.unsubscribeShipDespawn(this::onShipDespawn)
        }

        model.subscribeShipDespawn(this::onShipDespawn)
    }

    private fun onShipDespawn(ship: Ship) {
        for (pair in spritePairs) {
            if(pair.first === ship) {
                shipDied(ship)
                return
            }
        }
    }

    private fun shipDied(ship: Ship) {
        shipsLeft--
        if(shipsLeft <= 0) {
            onStageComplete()
        }
    }
}