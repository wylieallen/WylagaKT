package wylaga.model.entities.weapons

import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship

interface Weapon {
    fun fire(ship: Ship, hardpointX: Double, hardpointY: Double) : Collection<Projectile>
}