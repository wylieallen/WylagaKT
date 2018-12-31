package wylaga.model.entities.weapons

import wylaga.model.entities.Projectile
import wylaga.model.entities.ships.Ship

class NullWeapon private constructor() : Weapon {
    override fun fire(ship: Ship, hardpointX: Double, hardpointY: Double): Collection<Projectile> = setOf()

    companion object {
        val INSTANCE = NullWeapon()
    }
}