package wylaga.model.systems.movement

import wylaga.model.systems.Engine

class MovementEngine : Engine {
    private val entities = mutableSetOf<Movable>()

    fun add(entity: Movable) = entities.add(entity)
    fun remove(entity: Movable) = entities.remove(entity)

    override fun tick() = entities.forEach { entity -> entity.move(); }
}