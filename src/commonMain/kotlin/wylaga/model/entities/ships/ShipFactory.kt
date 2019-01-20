package wylaga.model.entities.ships

import wylaga.model.entities.Entity
import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.pilots.Pilot
import wylaga.model.entities.weapons.Weapon

class ShipFactory(private val onDeath: (Ship) -> Unit, private val spawnProjectile: (Projectile, Any) -> Unit, private val orientation: Entity.Orientation) {
    fun makeEnemy(x: Double = 256.0, y: Double = 256.0, weapon: Weapon, pilot: Pilot) : Ship {
        return Ship(x, y, 50.0, 50.0, 1.0, orientation, 80.0, 50, onDeath, 25.0, 2.0, pilot, spawnProjectile, weapon)
    }

    fun makeHardpointedPlayer(x: Double = 512.0, y: Double = 256.0, weapon: Weapon, pilot: Pilot) : Ship {
        return Ship(x, y, 50.0, 50.0, 3.0, orientation, 100.0, 100, onDeath, 25.0, 2.0, pilot, spawnProjectile, weapon)
    }

    fun makeSmallEnemy(x: Double, y: Double, weapon: Weapon, pilot: Pilot) : Ship {
        return Ship(x, y, 25.0, 25.0, 1.5, orientation, 50.0, 25, onDeath, 12.5, 2.0, pilot, spawnProjectile, weapon)
    }

    fun makeEvilPlayer(x: Double, y: Double, weapon: Weapon, pilot: Pilot) : Ship {
        return Ship(x, y, 50.0, 50.0, 3.0, orientation, 250.0, 1000, onDeath, 25.0, 2.0, pilot, spawnProjectile, weapon)
    }

    fun makeWingman(x: Double, y: Double, weapon: Weapon, pilot: Pilot) : Ship {
        return Ship(x, y, 25.0, 25.0, 3.0, orientation, 60.0, 25, onDeath, 12.5, 2.0, pilot, spawnProjectile, weapon)
    }
}