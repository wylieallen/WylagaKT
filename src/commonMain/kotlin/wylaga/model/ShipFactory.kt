package wylaga.model

import wylaga.model.entities.Entity
import wylaga.model.entities.Projectile
import wylaga.model.entities.pilots.Pilot
import wylaga.model.entities.ships.HardpointedShip
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.SimpleWeapon
import wylaga.model.entities.weapons.Weapon

class ShipFactory(private val onDeath: (Ship) -> Unit, private val onExpire: (Ship) -> Unit, private val spawnProjectile: (Projectile, Any) -> Unit) {
    fun makeEnemy(x: Double = 256.0, y: Double = 256.0, weapon: Weapon, pilot: Pilot) : Ship {
//        return Ship(x, y, 50.0, 50.0,2.0, Entity.Orientation.SOUTH,100.0, onDeath, onExpire, onFire)
        return HardpointedShip(x, y, 50.0, 50.0, 2.0, Entity.Orientation.SOUTH, 100.0, onDeath, onExpire, spawnProjectile, pilot, HardpointedShip.Hardpoint(25.0, 2.0, weapon))
    }

    fun makeHardpointedPlayer(x: Double = 512.0, y: Double = 256.0, weapon: Weapon, pilot: Pilot) : HardpointedShip {
        return HardpointedShip(x, y, 50.0, 50.0, 2.0, Entity.Orientation.NORTH, 100.0, onDeath, onExpire, spawnProjectile, pilot, HardpointedShip.Hardpoint(25.0, 2.0, weapon))
    }
}