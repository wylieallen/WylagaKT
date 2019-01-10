package wylaga.view.display.tickables

class CountdownTickable(private var counter: Int, private val countdownComplete: () -> Unit) : Tickable {
    override fun tick() {
        if(counter == 0) {
            countdownComplete()
        } else --counter
    }
}