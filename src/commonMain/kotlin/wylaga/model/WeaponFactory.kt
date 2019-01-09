package wylaga.model

import wylaga.model.entities.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.SimpleWeapon
import wylaga.model.entities.weapons.Weapon

class WeaponFactory(private val onImpact: (Projectile, Ship) -> Unit, private val onProjectileDespawn: (Projectile) -> Unit, private val onProjectileDisable: (Projectile) -> Unit) {
    fun makePlayerWeapon() : Weapon {
        return SimpleWeapon(4.0, 15.0, 6.0, onImpact, onProjectileDespawn, onProjectileDisable)
    }

    fun makeEnemyWeapon() : Weapon {
        return SimpleWeapon(7.0, 7.0, 9.0, onImpact, onProjectileDespawn, onProjectileDisable)
    }
}