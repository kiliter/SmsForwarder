package cn.ppps.forwarder.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import cn.ppps.forwarder.utils.Log
import cn.ppps.forwarder.utils.SettingUtils

@Suppress("PrivatePropertyName")
class SimStateReceiver : BroadcastReceiver() {

    private var TAG = SimStateReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action != "android.intent.action.SIM_STATE_CHANGED") return

        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        // 获取当前 SIM 卡状态
        val simStateNew = telephonyManager.simState

        val msg = when (simStateNew) {
            TelephonyManager.SIM_STATE_ABSENT -> {
                Log.d(TAG, "SIM 卡被移除")
                "SIM 卡被移除"
            }

            TelephonyManager.SIM_STATE_READY -> {
                Log.d(TAG, "SIM 卡已准备就绪")
                "SIM 卡已准备就绪"
            }

            else -> {
                Log.d(TAG, "SIM 卡状态未知")
                "SIM 卡状态未知"
            }

        }
        Log.d(TAG, "SIM state changed: $msg")
    }

}