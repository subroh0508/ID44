package id44.mizuki.libraries.api

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

class TokenExpiredException(val host: HostName, val id: AccountId) : Throwable()
