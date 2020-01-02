package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.timeline.domain.entity.Status

interface StatusRepository {
    suspend fun getPublicStatuses(maxId: String? = null, limit: Int = 20): List<Status>

    suspend fun getLocalStatuses(maxId: String? = null, limit: Int = 20): List<Status>
}
