interface Message {
    fun send()
}

class EmailMessage : Message {
    override fun send() = println("Sending Email")
}

class SmsMessage : Message {
    override fun send() = println("Sending SMS")
}

fun messageFactory(type: String): Message = when(type) {
    "email" -> EmailMessage()
    "sms" -> SmsMessage()
    else -> throw IllegalArgumentException("Unknown")
}