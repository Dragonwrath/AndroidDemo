package com.joker.tool.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.security.SecureRandom;
import java.util.UUID;

public class PhoneUtils {

	//该方法存在空指针，以及首位字符不为0的情况，导致问题出现
	public static String getDeviceUuid(Context context) {
		final TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId(); //能为空
		tmSerial = "" + tm.getSimSerialNumber(); //可能为空
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		return deviceUuid.toString();
	}

	public static String getDeviceRandomUUid(Context context){
		return UUID.randomUUID().toString(); //获取一个随机的UUID
	}

}
