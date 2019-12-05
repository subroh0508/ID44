package id44.mizuki.bridges.timeline

interface TimelineBridge {
    fun openAuthentication()

    fun fetchOwnAccounts(): List<Map<String, Any>>

    fun switchAccount(host: String, id: String)
}
