package id44.mizuki.libraries.shared.valueobject

import org.w3c.dom.url.URL

@JsName("Uri")
actual abstract class Uri(url: String) : URL(url)
