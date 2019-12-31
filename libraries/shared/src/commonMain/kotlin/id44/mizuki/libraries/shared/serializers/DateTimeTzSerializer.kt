package id44.mizuki.libraries.shared.serializers

import com.soywiz.klock.DateTimeTz
import id44.mizuki.libraries.shared.extensions.toDateTimeTz
import id44.mizuki.libraries.shared.extensions.toDateTimeTzFormat
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.SerialClassDescImpl

@Serializer(forClass = DateTimeTz::class)
class DateTimeTzSerializer : KSerializer<DateTimeTz> {
    override val descriptor = SerialClassDescImpl("DateTimeTz")

    override fun deserialize(decoder: Decoder): DateTimeTz =
        decoder.decodeString().toDateTimeTz()

    override fun serialize(encoder: Encoder, obj: DateTimeTz) =
        encoder.encodeString(obj.toDateTimeTzFormat())
}
