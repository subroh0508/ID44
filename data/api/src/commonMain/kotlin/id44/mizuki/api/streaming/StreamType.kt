package id44.mizuki.api.streaming

enum class StreamType(val realValue: String) {
    USER("user"),
    NOTIFICATION("user:notification"),
    PUBLIC("public"),
    PUBLIC_LOCAL("public:local"),
    HASHTAG("hashtag"),
    HASHTAG_LOCAL("hashtag:local"),
    LIST("list"),
    DIRECT("direct")
}
