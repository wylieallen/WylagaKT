package wylaga.model.entities.weapons

import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship

class NullWeapon private constructor() : Weapon {
    private val emptySet = setOf<Projectile>()

    override fun fire(ship: Ship, hardpointX: Double, hardpointY: Double): Collection<Projectile> = emptySet

    companion object {
        val INSTANCE = NullWeapon()
    }
}