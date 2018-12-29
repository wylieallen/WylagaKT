package wylaga.model.systems.movement

import wylaga.util.DirectionVector

interface Movable {
    var x: Double
    var y: Double
    var trajectory: DirectionVector
    var velocity: Double

    fun moveTo(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    fun moveBy(dx: Double, dy: Double) {
        x += dx
        y += dy
    }

    fun move() {
        moveBy(trajectory.dx * velocity, trajectory.dy * velocity)
    }
}