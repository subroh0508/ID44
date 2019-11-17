package id44.mizuki.libraries.api.json.account

interface AccountJsonFacade {
    val id: String
    val username: String
    val acct: String
    val displayName: String
    val locked: Boolean
    val createdAt: String
    val followersCount: Int
    val followingCount: Int
    val statusesCount: Int
    val note: String
    val url: String
    val avatar: String
    val avatarStatic: String
    val header: String
    val headerStatic: String
    val emojis: List<EmojiJson>?
    val fields: List<FieldJson>?
    val bot: Boolean
}
