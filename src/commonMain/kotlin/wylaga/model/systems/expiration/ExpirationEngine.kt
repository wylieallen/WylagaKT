package wylaga.model.systems.expiration

import wylaga.model.systems.Engine

class ExpirationEngine : Engine {
    private val expirables = mutableSetOf<Expirable>()

    fun add(entity: Expirable) {
        expirables.add(entity)
    }

    override fun tick() {
        expirables.forEach { it.expire() }
        expirables.clear()
    }
}