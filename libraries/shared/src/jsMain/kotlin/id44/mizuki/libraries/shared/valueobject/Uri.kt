package id44.mizuki.libraries.shared.valueobject

import org.w3c.dom.url.URL

@JsName("Uri")
actual abstract class Uri(url: String) : URL(url)
actual fun String.parseToUri(): Uri = UriImpl(this)

private class UriImpl(urlString: String) : Uri(urlString)
