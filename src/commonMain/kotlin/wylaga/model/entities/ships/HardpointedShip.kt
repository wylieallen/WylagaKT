package wylaga.model.entities.ships

import wylaga.model.entities.Entity
import wylaga.model.entities.Projectile
import wylaga.model.entities.weapons.NullWeapon
import wylaga.model.entities.weapons.Weapon

class HardpointedShip(x: Double, y: Double, width: Double, height: Double, velocity: Double, orientation: Entity.Orientation,
                      maxHealth: Double, onDeath: (Ship) -> Unit, onExpire: (Ship) -> Unit, spawnProjectile: (Projectile) -> Unit,
                      private val hardpoint: Hardpoint)
    : Ship(x, y, width, height, velocity, orientation, maxHealth, onDeath, onExpire, {hardpoint}) {

    class Hardpoint(private val x: Double, private val y: Double, var weapon: Weapon = NullWeapon.INSTANCE)
}