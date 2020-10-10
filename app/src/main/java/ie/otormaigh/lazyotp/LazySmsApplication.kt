package ie.otormaigh.lazyotp

import android.app.Application
import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import ie.otormaigh.lazyotp.data.LazySmsDatabase
import ie.otormaigh.lazyotp.service.BatteryLevelService
import ie.otormaigh.lazyotp.toolbox.batteryWarningEnabled
import ie.otormaigh.lazyotp.toolbox.settingsPrefs
import timber.log.Timber

class LazySmsApplication : Application() {
  val database by lazy {
    LazySmsDatabase(AndroidSqliteDriver(LazySmsDatabase.Schema, this, "lazy-sms.db"))
  }

  override fun onCreate() {
    super.onCreate()

    Timber.plant(object : Timber.DebugTree() {
      override fun createStackElementTag(element: StackTraceElement) =
        "(${element.fileName}:${element.lineNumber})"
    })

    if (settingsPrefs.batteryWarningEnabled) BatteryLevelService.start(this)
    else BatteryLevelService.stop(this)
  }
}

val Context.app: LazySmsApplication
  get() = applicationContext as LazySmsApplication