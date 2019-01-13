package wylaga.model.entities.ships

import wylaga.model.entities.Entity
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.pilots.Pilot
import wylaga.model.entities.weapons.NullWeapon
import wylaga.model.entities.weapons.Weapon

class HardpointedShip(x: Double, y: Double, width: Double, height: Double, velocity: Double, orientation: Entity.Orientation,
                      maxHealth: Double, points: Int, onDeath: (Ship) -> Unit, onExpire: (Ship) -> Unit, spawnProjectile: (Projectile, Any) -> Unit,
                      pilot: Pilot, private val hardpoint: Hardpoint)
    : Ship(x, y, width, height, velocity, orientation, maxHealth, points, onDeath, onExpire, {ship -> hardpoint.weapon.fire(ship, hardpoint.x, hardpoint.y).forEach{spawnProjectile(it, hardpoint.weapon)}}, pilot) {

    class Hardpoint(val x: Double, val y: Double, var weapon: Weapon = NullWeapon.INSTANCE)
}