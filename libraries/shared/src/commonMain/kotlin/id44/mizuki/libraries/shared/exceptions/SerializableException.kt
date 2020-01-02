package id44.mizuki.libraries.shared.exceptions

class SerializableException(
    val reason: Enum<*>,
    val originalMessage: String? = null
) : Throwable("${reason.name}: ${originalMessage ?: ""}")
