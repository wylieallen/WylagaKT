package wylaga.model

import wylaga.model.entities.Entity
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.pilots.Pilot
import wylaga.model.entities.ships.HardpointedShip
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.Weapon

class ShipFactory(private val onDeath: (Ship) -> Unit, private val onExpire: (Ship) -> Unit, private val spawnProjectile: (Projectile, Any) -> Unit) {
    fun makeEnemy(x: Double = 256.0, y: Double = 256.0, weapon: Weapon, pilot: Pilot) : Ship {
        return HardpointedShip(x, y, 50.0, 50.0, 1.0, Entity.Orientation.SOUTH, 80.0, 50, onDeath, onExpire, spawnProjectile, pilot, HardpointedShip.Hardpoint(25.0, 2.0, weapon))
    }

    fun makeHardpointedPlayer(x: Double = 512.0, y: Double = 256.0, weapon: Weapon, pilot: Pilot) : HardpointedShip {
        return HardpointedShip(x, y, 50.0, 50.0, 3.0, Entity.Orientation.NORTH, 100.0, 100, onDeath, onExpire, spawnProjectile, pilot, HardpointedShip.Hardpoint(25.0, 2.0, weapon))
    }

    fun makeSmallEnemy(x: Double, y: Double, weapon: Weapon, pilot: Pilot) : Ship {
        return HardpointedShip(x, y, 25.0, 25.0, 1.5, Entity.Orientation.SOUTH, 50.0, 25, onDeath, onExpire, spawnProjectile, pilot, HardpointedShip.Hardpoint(12.5, 2.0, weapon))
    }
}