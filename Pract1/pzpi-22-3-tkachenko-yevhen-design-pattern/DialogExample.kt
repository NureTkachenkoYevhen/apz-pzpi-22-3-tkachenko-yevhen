interface Dialog {
    fun render()
}

class WindowsDialog : Dialog {
    override fun render() = println("Rendering Windows dialog")
}

class LinuxDialog : Dialog {
    override fun render() = println("Rendering Linux dialog")
}

abstract class DialogFactory {
    abstract fun createDialog(): Dialog
}

class WindowsDialogFactory : DialogFactory() {
    override fun createDialog() = WindowsDialog()
}