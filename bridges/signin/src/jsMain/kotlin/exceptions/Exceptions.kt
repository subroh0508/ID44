package exceptions

import id44.mizuki.bridges.signin.exceptions.SignInError
import id44.mizuki.shared.exceptions.SerializableException


@JsName("parse")
fun parse(error: dynamic): SerializableException? = parse(SignInError.values(), error)

@JsName("parseKey")
fun parseKey(error: dynamic, defaultKey: String) = parse(error).key("signIn", defaultKey)
