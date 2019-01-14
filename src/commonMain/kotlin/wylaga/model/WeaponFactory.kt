package wylaga.model

import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.SimpleWeapon
import wylaga.model.entities.weapons.Weapon

class WeaponFactory(private val onProjectileDespawn: (Projectile) -> Unit, private val onProjectileDisable: (Projectile) -> Unit) {
    private fun onImpact(projectile: Projectile, ship: Ship, damage: Double) {
        ship.damage(damage)
        projectile.disable()
    }

    fun makePlayerWeapon(damage: Double) : Weapon {
        return SimpleWeapon(4.0, 15.0, 9.0, {projectile, ship -> onImpact(projectile, ship, damage) }, onProjectileDespawn, onProjectileDisable)
    }

    fun makeEnemyWeapon(damage: Double) : Weapon {
        return SimpleWeapon(7.0, 7.0, 9.0, {projectile, ship -> onImpact(projectile, ship, damage) }, onProjectileDespawn, onProjectileDisable)
    }
}