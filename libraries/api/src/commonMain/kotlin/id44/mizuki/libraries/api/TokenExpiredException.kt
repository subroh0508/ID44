package id44.mizuki.libraries.api

import id44.mizuki.shared.valueobject.AccountId
import id44.mizuki.shared.valueobject.HostName

class TokenExpiredException(val host: HostName, val id: AccountId) : Throwable()
