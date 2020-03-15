package exceptions

import id44.mizuki.shared.presentation.signin.exceptions.SignInError
import id44.mizuki.shared.util.exceptions.SerializableException


@JsName("parse")
fun parse(error: dynamic): SerializableException? = parse(SignInError.values(), error)

@JsName("parseKey")
fun parseKey(error: dynamic, defaultKey: String) = parse(error).key("signIn", defaultKey)
