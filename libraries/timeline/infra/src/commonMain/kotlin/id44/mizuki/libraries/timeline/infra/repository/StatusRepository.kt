package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.StatusVisibility

interface StatusRepository {
    suspend fun getLocalStatuses(maxId: String? = null, limit: Int = 20): List<Status>

    suspend fun getGlobalStatuses(maxId: String? = null, limit: Int = 20): List<Status>

    suspend fun postStatus(status: String, visibility: StatusVisibility = StatusVisibility.PUBLIC, warningText: String? = null): Status
    suspend fun postStatusForReply(replyToId: String, status: String, visibility: StatusVisibility = StatusVisibility.PUBLIC, warningText: String? = null): Status
    suspend fun postMedia(mediaIds: List<String>, sensitive: Boolean = false, status: String? = null, visibility: StatusVisibility = StatusVisibility.PUBLIC, warningText: String? = null): Status
}
