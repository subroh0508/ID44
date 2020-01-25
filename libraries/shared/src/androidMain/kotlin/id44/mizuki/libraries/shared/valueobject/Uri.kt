package id44.mizuki.libraries.shared.valueobject

actual typealias Uri = android.net.Uri
actual fun String.parseToUri(): Uri = Uri.parse(this)
