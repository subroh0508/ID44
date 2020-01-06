package id44.mizuki.libraries.timeline.infra.repository

import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse
import id44.mizuki.libraries.api.client.MastodonApi
import id44.mizuki.libraries.api.json.StatusJson
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.entity.Tooter
import id44.mizuki.libraries.timeline.domain.valueobject.StatusVisibility
import id44.mizuki.libraries.timeline.infra.toStatus
import id44.mizuki.libraries.timeline.infra.toStatusVisibilityType

class StatusRepositoryImpl(
    private val api: MastodonApi
) : StatusRepository {
    override suspend fun getGlobalStatuses(maxId: String?, limit: Int) =
        api.getTimelinesGlobal(maxId, limit).map { it.toStatus() }

    override suspend fun getLocalStatuses(maxId: String?, limit: Int) =
        api.getTimelinesLocal(maxId, limit).map { it.toStatus() }

    override suspend fun getHomeStatuses(maxId: String?, limit: Int) =
        api.getTimelinesHome(maxId, limit).map { it.toStatus() }

    override suspend fun postStatus(status: String, visibility: StatusVisibility, warningText: String?) =
        api.postStatus(
            status,
            visibility = visibility.toStatusVisibilityType(), spoilerText = warningText
        ).toStatus()
    override suspend fun postStatusForReply(
        replyToId: String, status: String,
        visibility: StatusVisibility, warningText: String?
    ) = api.postStatus(
        status,
        inReplyToId = replyToId,
        visibility = visibility.toStatusVisibilityType(), spoilerText = warningText
    ).toStatus()
    override suspend fun postMedia(
        mediaIds: List<String>, sensitive: Boolean,
        status: String?, visibility: StatusVisibility, warningText: String?
    ) = api.postStatus(
        status,
        mediaIds = mediaIds, sensitive = sensitive,
        visibility = visibility.toStatusVisibilityType(), spoilerText = warningText
    ).toStatus()
}
