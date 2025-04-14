abstract class Button {
    abstract fun render()
}

class AndroidButton : Button() {
    override fun render() = println("Android Button")
}

class IOSButton : Button() {
    override fun render() = println("iOS Button")
}

class ButtonFactory {
    fun getButton(type: String): Button = when (type) {
        "android" -> AndroidButton()
        "ios" -> IOSButton()
        else -> throw IllegalArgumentException("Unknown type")
    }
}