package id44.mizuki.shared.util

import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

sealed class Https {
    class UnauthorizedError(val host: HostName, val id: AccountId) : Throwable()
    class InternalServerError(val host: HostName, val id: AccountId) : Throwable()
    class ServiceTemporaryUnavailableError(val host: HostName, val id: AccountId) : Throwable()
    class Unknown(val host: HostName, val id: AccountId) : Throwable()
}
