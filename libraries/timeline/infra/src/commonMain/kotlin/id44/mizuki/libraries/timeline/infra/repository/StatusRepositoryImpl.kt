package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.api.client.MastodonApi
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.libraries.timeline.infra.toStatus
import id44.mizuki.libraries.timeline.infra.toStatusVisibilityType

class StatusRepositoryImpl(
    private val api: MastodonApi,
    private val cache: LocalCacheStore
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

    override suspend fun reblog(id: String) = api.reblog(id).toStatus(cache.getNowVerifyAccountsCredential()?.response?.id)
    override suspend fun favourite(id: String) = api.favourite(id).toStatus()
    override suspend fun unreblog(id: String) = api.unreblog(id).toStatus(cache.getNowVerifyAccountsCredential()?.response?.id)
    override suspend fun unfavourite(id: String) = api.unfavourite(id).toStatus()
}
