package id44.mizuki.libraries.api

import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

class TokenExpiredException(val host: HostName, val id: AccountId) : Throwable()
