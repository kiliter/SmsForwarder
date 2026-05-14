package cn.ppps.forwarder.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import cn.ppps.forwarder.R
import cn.ppps.forwarder.activity.MainActivity
import cn.ppps.forwarder.utils.ACTION_START
import cn.ppps.forwarder.utils.ACTION_STOP
import cn.ppps.forwarder.utils.ACTION_UPDATE_NOTIFICATION
import cn.ppps.forwarder.utils.CommonUtils
import cn.ppps.forwarder.utils.EXTRA_UPDATE_NOTIFICATION
import cn.ppps.forwarder.utils.FRONT_CHANNEL_ID
import cn.ppps.forwarder.utils.FRONT_CHANNEL_NAME
import cn.ppps.forwarder.utils.FRONT_NOTIFY_ID
import cn.ppps.forwarder.utils.Log
import cn.ppps.forwarder.utils.SettingUtils

@SuppressLint("SimpleDateFormat")
@Suppress("PrivatePropertyName", "DeferredResultUnused", "OPT_IN_USAGE", "DEPRECATION")
class ForegroundService : Service() {

    private val TAG: String = ForegroundService::class.java.simpleName
    private var notificationManager: NotificationManager? = null

    companion object {
        var isRunning = false
    }

    override fun onCreate() {
        super.onCreate()

        //纯客户端模式
        if (SettingUtils.enablePureClientMode) return

        //创建通知渠道
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //纯客户端模式
        if (SettingUtils.enablePureClientMode) return START_NOT_STICKY

        if (intent != null) {
            when (intent.action) {
                ACTION_START -> {
                    startForegroundService()
                }

                ACTION_STOP -> {
                    stopForegroundService()
                }

                ACTION_UPDATE_NOTIFICATION -> {
                    val updatedContent = intent.getStringExtra(EXTRA_UPDATE_NOTIFICATION)
                    updateNotification(updatedContent ?: "")
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        stopForegroundService()
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    //Created By AiCoding(codex)
    private fun startForegroundService() {
        if (isRunning) return
        isRunning = true

        val notification = createNotification(SettingUtils.notifyContent)
        startForeground(FRONT_NOTIFY_ID, notification)

        try {
            //开关通知监听服务
            if (SettingUtils.enableAppNotify && CommonUtils.isNotificationListenerServiceEnabled(this)) {
                CommonUtils.toggleNotificationListenerService(this)
            }
        } catch (e: Exception) {
            handleException(e, "startForegroundService")
        }
    }

    //Created By AiCoding(codex)
    private fun stopForegroundService() {
        try {
            stopForeground(true)
            stopSelf()
            isRunning = false
        } catch (e: Exception) {
            handleException(e, "stopForegroundService")
        }
    }

    private fun createNotificationChannel() {
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(FRONT_CHANNEL_ID, FRONT_CHANNEL_NAME, importance)
            notificationChannel.description = getString(R.string.notification_content)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            if (notificationManager != null) {
                notificationManager!!.createNotificationChannel(notificationChannel)
            }
        }
    }

    //Created By AiCoding(codex)
    private fun createNotification(content: String, largeIconResId: Int? = null): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val flags = if (Build.VERSION.SDK_INT >= 30) PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, flags)

        val builder = NotificationCompat.Builder(this, FRONT_CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_forwarder)
            .setContentIntent(pendingIntent)
            .setWhen(System.currentTimeMillis())

        // 设置大图标（可选）
        if (largeIconResId != null) {
            builder.setLargeIcon(BitmapFactory.decodeResource(resources, largeIconResId))
        } else {
            builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_menu_frpc))
        }

        return builder.build()
    }

    //Created By AiCoding(codex)
    private fun updateNotification(updatedContent: String, largeIconResId: Int? = null) {
        try {
            val notification = createNotification(updatedContent, largeIconResId)
            notificationManager?.notify(FRONT_NOTIFY_ID, notification)
        } catch (e: Exception) {
            handleException(e, "updateNotification")
        }
    }

    private fun handleException(e: Exception, methodName: String) {
        e.printStackTrace()
        Log.e(TAG, "$methodName: $e")
        isRunning = false
    }

}
