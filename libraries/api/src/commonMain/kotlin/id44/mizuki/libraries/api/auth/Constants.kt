package id44.mizuki.libraries.api.auth

object Constants {
    const val CLIENT_NAME = "id44.mizuki"
    const val OAUTH_SCHEME = "oauth2redirect"
    const val REDIRECT_URI = "$OAUTH_SCHEME://$CLIENT_NAME/"
    const val SCOPE = "read write follow push"
    const val WEBSITE = "https://github.com/subroh0508/ID44"
    const val ESCAPED_SCOPE = "read%20write%20follow%20push"
}
