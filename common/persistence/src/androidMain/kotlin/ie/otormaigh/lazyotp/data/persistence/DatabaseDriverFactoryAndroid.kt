package ie.otormaigh.lazyotp.data.persistence

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import ie.otormaigh.lazyotp.data.LazySmsDatabase

actual class DatabaseDriverFactory(private val context: Context) {
  actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(LazySmsDatabase.Schema, context, "lazy-sms.db")
  }
}