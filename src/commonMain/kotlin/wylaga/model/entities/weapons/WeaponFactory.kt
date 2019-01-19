package wylaga.model.entities.weapons

import wylaga.model.entities.projectiles.Projectile
import wylaga.model.entities.ships.Ship
import wylaga.model.systems.expiration.Cause

class WeaponFactory(private val onProjectileDisable: (Projectile, Cause) -> Unit) {
    private fun onImpact(projectile: Projectile, ship: Ship, damage: Double) {
        ship.damage(damage)
        projectile.disable(Cause.IMPACT)
    }

    fun makePlayerWeapon(damage: Double) : Weapon {
        return SimpleWeapon(4.0, 15.0, 9.0, {projectile, ship -> onImpact(projectile, ship, damage) }, onProjectileDisable)
    }

    fun makeEnemyWeapon(damage: Double) : Weapon {
        return SimpleWeapon(7.0, 7.0, 9.0, {projectile, ship -> onImpact(projectile, ship, damage) }, onProjectileDisable)
    }
}