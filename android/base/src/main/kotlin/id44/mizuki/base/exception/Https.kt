package id44.mizuki.base.exception

sealed class Https {
    class UnauthorizedError(val hostName: String) : Throwable()
    class InternalServerError(val hostName: String) : Throwable()
    class ServiceTemporaryUnavailableError(val hostName: String) : Throwable()
    class Unknown(val hostName: String) : Throwable()
}