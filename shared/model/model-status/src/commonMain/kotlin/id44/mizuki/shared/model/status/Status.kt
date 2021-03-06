package id44.mizuki.shared.model.status

import com.soywiz.klock.DateTimeTz
import id44.mizuki.shared.util.serializers.DateTimeTzSerializer
import id44.mizuki.shared.model.status.StatusVisibility
import kotlinx.serialization.Serializable

@Serializable
class Status(
    val id: String,
    val content: String,
    val warningText: String,
    @Serializable(with = DateTimeTzSerializer::class)
    val createdAt: DateTimeTz,
    val visibility: StatusVisibility,
    val repliesCount: Int?,
    val favouriteCount: Int,
    val reblogCount: Int,
    val favourited: Boolean,
    val reblogged: Boolean,
    val tooter: Tooter,
    val rebloggedBy: Tooter?
) {
    val isWarningContent get() = warningText.isNotBlank()
}
