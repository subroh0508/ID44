package id44.mizuki.signin

class AccessDeniedError : Throwable()
class AuthorizeError(message: String?) : Throwable(message)
class UnknownError(message: String?) : Throwable(message)
class BrowserAppNotFoundError : Throwable()
