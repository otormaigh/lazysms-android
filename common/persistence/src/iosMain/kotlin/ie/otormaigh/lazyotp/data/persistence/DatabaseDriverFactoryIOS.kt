package ie.otormaigh.lazyotp.data.persistence

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import ie.otormaigh.lazyotp.data.LazySmsDatabase

actual class DatabaseDriverFactory {
  actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(LazySmsDatabase.Schema, "lazy-sms.db")
  }
}