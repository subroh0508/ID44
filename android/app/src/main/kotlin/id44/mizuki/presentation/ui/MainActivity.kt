package id44.mizuki.presentation.ui

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.os.bundleOf
import com.facebook.react.ReactInstanceManager
import com.facebook.react.common.LifecycleState
import com.facebook.react.shell.MainReactPackage
import id44.mizuki.BuildConfig
import id44.mizuki.R
import id44.mizuki.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val OVERLAY_PERMISSION_REQUEST = 100
        private val COLORS = listOf("#000000", "#0000FF", "#00FF00", "#FF0000")
    }

    private lateinit var reactInstanceManager: ReactInstanceManager
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                requestOverlayPermission()
                return
            }

            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit {
                putString("debug_http_host", BuildConfig.DEV_HOST)
            }
        }

        startActivity(Intent(this, SignInActivity::class.java))
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun requestOverlayPermission() {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$packageName")
        )

        startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == OVERLAY_PERMISSION_REQUEST) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "You need permission for overlay", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit {
                putString("debug_http_host", BuildConfig.DEV_HOST)
            }
            startActivity(Intent(this, SignInActivity::class.java))
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setReactRootView() {
        reactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackage(MainReactPackage())
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

        reactRootView.startReactApplication(
            reactInstanceManager,
            "App",
            bundleOf("styles" to buildCss())
        )

        setClickListener()
    }

    private fun setClickListener() {
        change.setOnClickListener {
            count += 1

            val updatedProps = reactRootView.appProperties ?: bundleOf()

            updatedProps.putString("styles", buildCss(COLORS[count % 4]))
            reactRootView.appProperties = updatedProps
        }
    }

    private fun buildCss(color: String = "#000000"): String
            = "{\"container\":{\"flex\":1,\"justifyContent\":\"center\"},\"hello\":{\"fontSize\":20,\"textAlign\":\"center\",\"margin\":10,\"color\":\"$color\"}}"
}
