external object ElectronApp {
    fun getPath(path: String): String
}

external object ElectronShell {
    fun openExternal(uri: String)
}
