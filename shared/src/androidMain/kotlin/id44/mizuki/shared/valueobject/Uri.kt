package id44.mizuki.shared.valueobject

actual inline class Uri(val value: String)

fun Uri.parse(): android.net.Uri = android.net.Uri.parse(value)


