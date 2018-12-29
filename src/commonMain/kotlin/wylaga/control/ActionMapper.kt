package wylaga.control

class ActionMapper {
    private val map = mutableMapOf<Action, () -> Unit>()

    val execute: (Action) -> Unit

    init {
        execute = {map.getOrElse(it){ {} }()}
    }

    fun bind(action: Action, function: () -> Unit) {
        map[action] = function
    }

    fun unbind(action: Action) {
        map.remove(action)
    }
}