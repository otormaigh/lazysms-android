package ie.otormaigh.lazyotp.data.persistence

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
  fun createDriver(): SqlDriver
}