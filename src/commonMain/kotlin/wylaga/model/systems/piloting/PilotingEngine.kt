package wylaga.model.systems.piloting

import wylaga.model.systems.Engine

class PilotingEngine : Engine {
    private val pilotables = mutableSetOf<Pilotable>()

    override fun tick() = pilotables.forEach(Pilotable::pilot)

    fun add(entity: Pilotable) = pilotables.add(entity)
    fun remove(entity: Pilotable) = pilotables.remove(entity)
}