package id44.mizuki.shared.util.exceptions

class SerializableException(
    val reason: Enum<*>,
    val originalMessage: String? = null
) : Throwable("${reason.name}: ${originalMessage ?: ""}")
