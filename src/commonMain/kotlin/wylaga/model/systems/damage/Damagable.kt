package wylaga.model.systems.damage

interface Damagable {
    fun damage(damage: Double)
    fun heal(healing: Double)
}