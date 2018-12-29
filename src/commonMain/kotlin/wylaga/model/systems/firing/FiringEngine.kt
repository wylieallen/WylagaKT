package wylaga.model.systems.firing

import wylaga.model.systems.Engine

class FiringEngine : Engine {
    private val fireables = mutableSetOf<Fireable>()

    fun add(entity: Fireable) = fireables.add(entity)
    fun remove(entity: Fireable) = fireables.remove(entity)

    override fun tick() = fireables.forEach { it.fire() }
}