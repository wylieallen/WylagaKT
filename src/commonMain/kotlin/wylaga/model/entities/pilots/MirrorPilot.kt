package wylaga.model.entities.pilots

import wylaga.model.entities.ships.Ship
import wylaga.util.DirectionVector

class MirrorPilot(private val target: Ship, delay: Int) : Pilot {
    private val trajectoryQueue = mutableListOf<DirectionVector>()
    private val energyQueue = mutableListOf<Double>()
    private val boostQueue = mutableListOf<Boolean>()
    private val fireQueue = mutableListOf<Boolean>()

    init {
        for(i in 1..delay) {
            trajectoryQueue.add(DirectionVector.NULL)
            energyQueue.add(200.0)
            boostQueue.add(false)
            fireQueue.add(false)
        }
    }

    private fun enqueueTargetState() {
        trajectoryQueue.add(target.trajectory)
        energyQueue.add(target.energy)
        boostQueue.add(target.wantsToBoost)
        fireQueue.add(target.wantsToFire)
    }

    override fun update(ship: Ship) {
        enqueueTargetState()

        ship.trajectory = trajectoryQueue.removeAt(0)
        ship.energy = energyQueue.removeAt(0)
        ship.wantsToBoost = boostQueue.removeAt(0)
        ship.wantsToFire = fireQueue.removeAt(0)
    }
}