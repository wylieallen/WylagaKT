package wylaga.view.display

interface Painter {
    fun push()
    fun pop()

    fun translate(x: Double, y: Double)
    fun rotate(theta: Double)

    fun setColor(color: Color)
    fun setFont(typeface: String, size: Int)

    fun fillRect(x: Double, y: Double, width: Double, height: Double)
    fun drawString(string: String)
}