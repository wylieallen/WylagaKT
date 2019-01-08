package wylaga.model.systems.boosting

import wylaga.model.systems.Engine

class BoostingEngine() : Engine {
    private val boostables = mutableSetOf<Boostable>()

    override fun tick() = boostables.forEach(Boostable::boost)

    fun add(boostable: Boostable) = boostables.add(boostable)
    fun remove(boostable: Boostable) = boostables.remove(boostable)
}