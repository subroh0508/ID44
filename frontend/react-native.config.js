const APP_SRC_MAIN = '../android/app/src/main';

module.exports = {
    project: {
        android: {
            sourceDir: '../android',
            stringsPath: `${APP_SRC_MAIN}/res/values/strings.xml`,
            manifestPath: `${APP_SRC_MAIN}/AndroidManifest.xml`,
            buildGradlePath: '../../build.gradle.kts',
            settingsGradlePath: '../../settings.gradle.kts',
            assetsPath: `${APP_SRC_MAIN}/assets`,
            mainFilePath: `${APP_SRC_MAIN}/kotlin/id44/mizuki/MainApplication.kt`,
        },
    },
};
