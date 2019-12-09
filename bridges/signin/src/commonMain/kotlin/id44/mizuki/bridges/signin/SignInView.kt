package id44.mizuki.bridges.signin

import id44.mizuki.libraries.shared.valueobject.Uri

interface SignInView {
    val clientName: String
    val redirectUri: Uri
}
