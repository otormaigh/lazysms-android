package ie.otormaigh.lazyotp.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import ie.otormaigh.lazyotp.BuildConfig
import ie.otormaigh.lazyotp.api.Api
import ie.otormaigh.lazyotp.toolbox.deviceName
import ie.otormaigh.lazyotp.toolbox.settingsPrefs
import ie.otormaigh.lazyotp.toolbox.slackToken

class SlackPostWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
  override suspend fun doWork(): Result {
    val deviceName = applicationContext.settingsPrefs.deviceName
    val sender = inputData.getString(ARG_SENDER)
    val smsCode = inputData.getString(ARG_SMS_CODE)
    val message = """
        {
          "attachments": [
            {
              "footer": "Version: ${BuildConfig.VERSION_NAME}",
              "fields": [
                {
                  "title" : "Phone",
                  "value" : "$deviceName",
                  "short" : true
                },
                {
                  "title" : "Sender",
                  "value" : "$sender",
                  "short" : true
                },
                {
                  "title" : "Code",
                  "value" : "$smsCode",
                  "short" : true
                }
              ]
            },
          ]
        }
    """.trimIndent()

    Api.instance.postSmsCode(
      message,
      applicationContext.settingsPrefs.slackToken.takeIf { it.isNotEmpty() } ?: BuildConfig.SLACK_TOKEN
    ).await()

    return Result.success()
  }

  companion object {
    private const val ARG_SMS_CODE = "arg.sms_code"
    private const val ARG_SENDER = "arg.sender"

    fun data(sender: String, smsCode: String) = Data.Builder().apply {
      putString(ARG_SENDER, sender)
      putString(ARG_SMS_CODE, smsCode)
    }.build()
  }
}