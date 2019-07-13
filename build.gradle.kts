allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
