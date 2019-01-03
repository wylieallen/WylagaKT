package wylaga.input

class ActionMapper {
    private val map = mutableMapOf<Action, () -> Unit>()

    fun execute(action: Action) = map.getOrElse(action){ {} }()
    fun bind(action: Action, function: () -> Unit) = map.set(action, function)
    fun unbind(action: Action) = map.remove(action)
}