package wylaga

import wylaga.view.display.Color
import kotlin.test.Test
import wylaga.view.display.Painter
import wylaga.view.display.image.Base64Encoding
import wylaga.view.display.displayables.composites.CompositeDisplayable

class WylagaTests {
    private val wylaga = Wylaga(this::mockDecoder)

    @Test
    fun testTick() {
        wylaga.tick()
    }

    @Test
    fun testDisplay() {
        wylaga.display(MockPainter())
    }

    private fun mockDecoder(encoding: Base64Encoding) : CompositeDisplayable {
        return CompositeDisplayable()
    }

    private class MockPainter : Painter {
        override fun push() {}

        override fun pop() {}

        override fun translate(x: Double, y: Double) {}

        override fun rotate(theta: Double) {}

        override fun setFillColor(color: Color) {}

        override fun fillRect(x: Double, y: Double, width: Double, height: Double) {}
    }
}