package wylaga.stages

import wylaga.model.Model
import wylaga.model.entities.Entity
import wylaga.model.entities.ships.Ship
import wylaga.view.View
import wylaga.view.sprites.Sprite

interface Stage {
    //fun loadAssets(setSprite: (Entity, Sprite) -> Unit, setSpriteMaker: (Any, (Entity) -> Sprite) -> Unit)
    fun load(view: View)
    fun start(model: Model)
}