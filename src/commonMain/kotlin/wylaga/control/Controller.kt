package wylaga.control

import wylaga.input.Action
import wylaga.input.CombatActionHandler
import wylaga.input.DpadParser
import wylaga.model.entities.pilots.Pilot

class Controller(controlled: Pilot) {
    private val inputController = DpadParser(controlled)
    private val actionHandler = CombatActionHandler(inputController)

    fun press(action: Action) = actionHandler.press(action)
    fun release(action: Action) = actionHandler.release(action)
}