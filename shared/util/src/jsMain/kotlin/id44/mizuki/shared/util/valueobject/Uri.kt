package id44.mizuki.shared.util.valueobject

import org.w3c.dom.url.URL

@JsName("Uri")
actual inline class Uri(val value: String)

fun Uri.parse(): URL = URL(value)
