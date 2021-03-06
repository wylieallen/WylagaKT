package wylaga.model.entities.pickups

import wylaga.model.entities.ships.Ship
import wylaga.model.entities.weapons.Weapon
import wylaga.model.systems.expiration.Cause
import wylaga.util.DirectionVector

class PickupFactory(private val onDisable: (Pickup, Cause) -> Unit, private val addToScore: (Int) -> Unit, private val weaponUpgrades: Array<Weapon>,
                    private val spawnWingmen: (Ship) -> Unit, private val spawnSuperWingmen: (Ship) -> Unit) {

    private var weaponLevel = 0

    fun makeHealing(x: Double, y: Double) = makePickup(x, y) { addToScore(20); it.heal(it.maxHealth * 0.2); }
    fun makeEnergy(x: Double, y: Double) = makePickup(x, y) { addToScore(20); it.energy = it.maxEnergy; }
    fun makePoints(x: Double, y: Double) = makePickup(x, y) { addToScore(100); }
    fun makeWeaponUpgrade(x: Double, y: Double) = makePickup(x, y) {
        addToScore(200)
        it.hardpoint.weapon = weaponUpgrades[weaponLevel]
        if(weaponLevel < weaponUpgrades.size - 1) {
            weaponLevel++
        }
    }
    fun makeFullHealing(x: Double, y: Double) = makePickup(x, y) { addToScore(50); it.heal(it.maxHealth); }
    fun makeHealthUpgrade(x: Double, y: Double) = makePickup(x, y) { addToScore(100); it.maxHealth += 10; it.heal(10.0); }
    fun makeWingmen(x: Double, y: Double) = makePickup(x, y) { addToScore(50); spawnWingmen(it); }
    fun makeSuperWingmen(x: Double, y: Double) = makePickup(x, y) { addToScore(200); spawnSuperWingmen(it); }

    private fun makePickup(x: Double, y: Double, effect: (Ship) -> Unit) = Pickup(x - 7.5, y - 7.5, 15.0, 15.0, DirectionVector.SOUTH, 1.0, onDisable, effect)
}