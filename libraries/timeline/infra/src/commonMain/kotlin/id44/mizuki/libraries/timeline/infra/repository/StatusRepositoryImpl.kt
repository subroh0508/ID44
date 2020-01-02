package id44.mizuki.libraries.timeline.infra.repository

import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse
import id44.mizuki.libraries.api.client.MastodonApi
import id44.mizuki.libraries.api.json.StatusJson
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.entity.Tooter

class StatusRepositoryImpl(
    private val api: MastodonApi
) : StatusRepository {
    override suspend fun getLocalStatuses(maxId: String?, limit: Int) =
        api.getTimelinesPublic(maxId, limit).map { it.toStatus() }

    override suspend fun getPublicStatuses(maxId: String?, limit: Int) =
        api.getTimelinesLocal(maxId, limit).map { it.toStatus() }

    private fun StatusJson.toStatus() = Status(
        id = id,
        content = content,
        createdAt = DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(createdAt),
        favouriteCount = favouritesCount,
        reblogCount = reblogsCount,
        tooter = with (account) {
            Tooter(id, username, displayName, url, avatar, avatarStatic)
        }
    )
}
