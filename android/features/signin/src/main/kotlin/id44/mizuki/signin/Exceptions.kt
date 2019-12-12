package id44.mizuki.signin

enum class SignInError {
    ACCESS_DENIED, AUTHORIZE_FAILED, UNKNOWN, BROWSER_APP_NOT_FOUND
}

class SignInException(reason: SignInError, message: String? = null) : Throwable("$reason: $message")

class AccessDeniedError : Throwable()
class AuthorizeError(message: String?) : Throwable(message)
class UnknownError(message: String?) : Throwable(message)
class BrowserAppNotFoundError : Throwable()
