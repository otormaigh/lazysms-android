package ie.otormaigh.lazyotp.data.persistence

import ie.otormaigh.lazyotp.data.LazySmsDatabase

class Database(databaseDriverFactory: DatabaseDriverFactory) {
  private val database = LazySmsDatabase(databaseDriverFactory.createDriver())
}