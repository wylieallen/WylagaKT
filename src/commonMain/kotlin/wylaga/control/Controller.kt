package wylaga.control

import wylaga.external.Action

interface Controller {
    fun press(action: Action)
    fun release(action: Action)
}