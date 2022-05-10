package ru.burtsev.push_keeper

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.service.notification.StatusBarNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.domain.NotificationInteractor

const val EXTRA_TITLE = "android.title"
const val EXTRA_TEXT = "android.text"

class NotificationListenerService : android.service.notification.NotificationListenerService() {

    private val notificationInteractor: NotificationInteractor by inject()

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName ?: ""
        val title: String = sbn.notification.extras[EXTRA_TITLE] as? String ?: ""
        val text: String = sbn.notification.extras[EXTRA_TEXT] as? String ?: ""
        val time: Long = System.currentTimeMillis()
        val entity = NotificationEntity(
            id = 0,
            packages = packageName,
            appName = getAppNameFromPkgName(applicationContext, packageName),
            title = title,
            text = text,
            time = time
        )

        if (text.isNotBlank() && title.isNotBlank()) {
            CoroutineScope(Dispatchers.IO + Job()).launch {
                notificationInteractor.insertNotifications(entity)
            }
        }
    }

    private fun getAppNameFromPkgName(context: Context, packageName: String?): String {
        return try {
            val packageManager: PackageManager = context.packageManager
            val info =
                packageManager.getApplicationInfo(packageName!!, PackageManager.GET_META_DATA)
            packageManager.getApplicationLabel(info) as String
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }
}
