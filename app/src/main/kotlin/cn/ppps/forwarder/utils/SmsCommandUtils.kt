package cn.ppps.forwarder.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.core.app.ActivityCompat
import cn.ppps.forwarder.App
import com.xuexiang.xrouter.utils.TextUtils
import com.xuexiang.xutil.XUtil
import com.xuexiang.xutil.security.CipherUtils
import com.xuexiang.xutil.system.DeviceUtils

@Suppress("OPT_IN_USAGE", "DeferredResultUnused", "DEPRECATION")
class SmsCommandUtils {

    companion object {

        var TAG = "SmsCommandUtils"

        //检查短信指令
        fun check(smsContent: String): Boolean {
            return smsContent.startsWith("smsf#")
        }

        //执行短信指令
        //Created By AiCoding(codex)
        fun execute(context: Context, smsCommand: String): Boolean {
            val cmdList = smsCommand.split("#", limit = 3)
            Log.d(TAG, "smsCommand = $smsCommand, cmdList = $cmdList")
            if (cmdList.count() < 2) return false

            val function = cmdList[0]
            val action = cmdList[1]
            val param = if (cmdList.count() > 2) cmdList[2] else ""
            when (function) {
                "system" -> {
                    //判断是否已root
                    if (!DeviceUtils.isDeviceRooted()) return false

                    // 过滤重复消息机制
                    var duplicateMessagesLimits = SettingUtils.duplicateMessagesLimits * 1000L
                    if (duplicateMessagesLimits > 0L) {
                        duplicateMessagesLimits += 10000L //系统指令多加10秒避免误操作
                        val key = CipherUtils.md5(smsCommand)
                        val timestamp: Long = System.currentTimeMillis()
                        var timestampPrev: Long by HistoryUtils(key, timestamp)
                        Log.d(TAG, "duplicateMessagesLimits=$duplicateMessagesLimits, timestamp=$timestamp, timestampPrev=$timestampPrev, msgInfo=$smsCommand")
                        if (timestampPrev != timestamp && timestamp - timestampPrev <= duplicateMessagesLimits) {
                            Log.e(TAG, "过滤重复消息机制")
                            timestampPrev = timestamp
                            return false
                        }
                        timestampPrev = timestamp
                    }

                    if (action == "reboot") {
                        DeviceUtils.reboot()
                    } else if (action == "shutdown") {
                        DeviceUtils.shutdown()
                    }
                }

                "wifi" -> {
                    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                    if (action == "on") {
                        wifiManager.isWifiEnabled = true
                    } else if (action == "off") {
                        wifiManager.isWifiEnabled = false
                    }
                }

                "sms" -> {
                    // TODO: 远程短信发送功能已移除，SmsSendData 类已被删除
                    Log.d(TAG, "sms send command not supported")
                    return false
                }
            }

            return true
        }
    }
}
