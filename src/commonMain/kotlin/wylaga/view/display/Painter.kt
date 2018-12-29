package wylaga.view.display

interface Painter {
    fun push()
    fun pop()

    fun translate(x: Double, y: Double)
    fun rotate(theta: Double)

    fun setFillColor(color: Color)
    fun fillRect(x: Double, y: Double, width: Double, height: Double)
}