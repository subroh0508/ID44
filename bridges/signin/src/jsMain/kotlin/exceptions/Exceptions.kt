package exceptions

import id44.mizuki.bridges.signin.exceptions.SignInError
import id44.mizuki.libraries.shared.exceptions.SerializableException

@JsName("parse")
fun parse(error: dynamic): SerializableException? = parse(SignInError.values(), error)

@JsName("key")
fun SerializableException?.key(defaultKey: String) = key("signIn", defaultKey)
