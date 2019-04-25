package ie.elliot.lazysms.feature.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ie.elliot.lazysms.R

class SettingsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, SettingsFragment()).commit()
  }
}