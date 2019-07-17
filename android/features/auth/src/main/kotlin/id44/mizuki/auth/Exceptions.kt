package id44.mizuki.auth

class AccessDeniedError : Throwable()
class AuthorizeError(message: String?) : Throwable(message)
class UnknownError(message: String?) : Throwable(message)
class BrowserAppNotFoundError : Throwable()
