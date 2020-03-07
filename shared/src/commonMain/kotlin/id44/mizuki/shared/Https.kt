package id44.mizuki.shared

import id44.mizuki.shared.valueobject.AccountId
import id44.mizuki.shared.valueobject.HostName

sealed class Https {
    class UnauthorizedError(val host: HostName, val id: AccountId) : Throwable()
    class InternalServerError(val host: HostName, val id: AccountId) : Throwable()
    class ServiceTemporaryUnavailableError(val host: HostName, val id: AccountId) : Throwable()
    class Unknown(val host: HostName, val id: AccountId) : Throwable()
}
