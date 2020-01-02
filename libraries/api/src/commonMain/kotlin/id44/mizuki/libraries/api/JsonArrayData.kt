package id44.mizuki.libraries.api

interface JsonArrayData {
    val raw: RawJsonArray

    operator fun get(index: Int) = raw[index]
}
