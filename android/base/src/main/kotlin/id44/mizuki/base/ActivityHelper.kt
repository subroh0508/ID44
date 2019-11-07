package id44.mizuki.base

import android.content.Intent

private const val PACKAGE_NAME = "id44.mizuki"

fun intentTo(addressableActivity: AddressableActivity): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, addressableActivity.className)

interface AddressableActivity {
    val className: String
}

object Activities {
    object Authentication : AddressableActivity {
        override val className = "$PACKAGE_NAME.auth.presentation.ui.AuthenticationActivity"
    }

    object Timeline : AddressableActivity {
        override val className = "$PACKAGE_NAME.timeline.presentation.ui.TimelineActivity"
    }
}
