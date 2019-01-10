package wylaga.view.display.tickables.primitives

import wylaga.view.display.tickables.Tickable

class IntervalTickable(private val interval: Int, private val onInterval: () -> Unit) : Tickable {
    private var counter = interval

    override fun tick() {
        if(counter <= 0) {
            counter = interval
            onInterval()
        } else --counter
    }
}