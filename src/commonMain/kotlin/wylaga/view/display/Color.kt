package wylaga.view.display

class Color(val red: Int, val green: Int, val blue: Int, val alpha: Int = 0xFF) {

    fun withAlpha(alpha: Int): Color {
        return Color(red, green, blue, alpha)
    }

    companion object {
        val BLACK = Color(0x00, 0x00, 0x00)
        val WHITE = Color(0xFF, 0xFF, 0xFF)
        val GRAY = Color(0x80, 0x80, 0x80)
        val RED = Color(0xFF, 0x00, 0x00)
        val GREEN = Color(0x00, 0xFF, 0x00)
        val BLUE = Color(0x00, 0x00, 0xFF)
        val YELLOW = Color(0xFF, 0xFF, 0x00)
        val MAGENTA = Color(0xFF, 0x00, 0xFF)
        val CYAN = Color(0x00, 0xFF, 0xFF)
        val PINK = Color(0xFF, 0xC0, 0xCB)
        val ORANGE = Color(0xFF, 0xA5, 0x00)
    }
}