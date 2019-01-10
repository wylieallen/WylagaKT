package wylaga.view.display.tickables

class DelegateTickable(var delegate: Tickable = NullTickable.instance) : Tickable {
    override fun tick() = delegate.tick()
}