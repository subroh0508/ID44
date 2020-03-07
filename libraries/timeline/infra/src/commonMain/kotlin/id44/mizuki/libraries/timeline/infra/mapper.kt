package id44.mizuki.libraries.timeline.infra

import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse
import id44.mizuki.api.json.StatusJson
import id44.mizuki.api.json.enums.StatusVisibilityType
import id44.mizuki.api.streaming.StreamType
import id44.mizuki.api.streaming.json.EventType
import id44.mizuki.api.streaming.json.StreamingEventJson
import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Tooter
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.shared.model.status.Stream

internal fun Stream.toStreamType(): StreamType = when (this) {
    Stream.DIRECT -> StreamType.DIRECT
    Stream.HOME -> StreamType.USER
    Stream.LOCAL -> StreamType.PUBLIC_LOCAL
    Stream.GLOBAL -> StreamType.PUBLIC
    Stream.HASH_TAG_LOCAL -> StreamType.HASHTAG_LOCAL
    Stream.HASH_TAG_GLOBAL -> StreamType.HASHTAG
    Stream.LIST -> StreamType.LIST
}

internal fun StatusVisibility.toStatusVisibilityType() = StatusVisibilityType.valueOf(name.toLowerCase())

internal fun StreamingEventJson.toStatus(): Status? {
    if (EventType.realValueOf(event) != EventType.update) {
        return null
    }

    return payload?.let(::StatusJson)?.toStatus()
}

internal fun StatusJson.toStatus(nowAccountId: String? = null) = Status(
    id = reblog?.id ?: id,
    content = reblog?.content ?: content,
    warningText = spoilerText,
    createdAt = DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(reblog?.createdAt ?: createdAt),
    visibility = StatusVisibility.valueOf(visibility.toUpperCase()),
    repliesCount = (reblog?.repliesCount ?: repliesCount)?.toInt(),
    favouriteCount = reblog?.favouritesCount ?: favouritesCount,
    reblogCount = reblog?.reblogsCount ?: reblogsCount,
    favourited = (reblog?.favourited ?: favourited) ?: false,
    reblogged = (reblog?.reblogged ?: reblogged) ?: false,
    tooter = with (reblog?.account ?: account) { Tooter(id, username, displayName, url, avatar, avatarStatic) },
    rebloggedBy =
        if (reblog != null && account.id != nowAccountId)
            with (account) { Tooter(id, username, displayName, url, avatar, avatarStatic) }
        else
            null
)
