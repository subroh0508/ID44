package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

interface StatusRepository {
    suspend fun receiveStatus(hostName: String, stream: Stream): Status
}
