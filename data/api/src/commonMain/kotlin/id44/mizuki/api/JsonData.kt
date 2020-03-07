package id44.mizuki.api

interface JsonData {
    val raw: RawJson

    operator fun get(key: String) = raw[key]
}
