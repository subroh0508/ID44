package id44.mizuki.libraries.timeline.infra

import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse
import id44.mizuki.libraries.api.json.StatusJson
import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.EventType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.entity.Tooter
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

internal fun Stream.toStreamType(): StreamType = when (this) {
    Stream.DIRECT -> StreamType.DIRECT
    Stream.HOME -> StreamType.USER
    Stream.LOCAL -> StreamType.PUBLIC_LOCAL
    Stream.GLOBAL -> StreamType.PUBLIC
    Stream.HASH_TAG_LOCAL -> StreamType.HASHTAG_LOCAL
    Stream.HASH_TAG_GLOBAL -> StreamType.HASHTAG
    Stream.LIST -> StreamType.LIST
}

internal fun StreamingEventJson.toStatus(): Status? {
    if (EventType.realValueOf(event) != EventType.update) {
        return null
    }

    val payload = payload?.let(StatusJson.Companion::fromJson) ?: return null

    return Status(
        id = payload.id,
        content = payload.content,
        createdAt = DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(payload.createdAt),
        favouriteCount = payload.favouritesCount,
        reblogCount = payload.reblogsCount,
        tooter = with (payload.account) {
            Tooter(id, username, displayName, url, avatar, avatarStatic)
        }
    )
}
