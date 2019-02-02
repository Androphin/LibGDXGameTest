package com.orbit.mygame;

import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.Locale;


public class AndroidDeviceData implements DeviceData {

    private Context appContext;

    TelephonyManager telephonyManager;
    WifiManager wifiManager;

    public AndroidDeviceData(Context c){
        this.appContext = c;
    }

    //ISO3 Country Code
    @Override
    public String getCountryCode() {
        return Locale.getDefault().getISO3Country();
    }

    //Android Version
    @Override
    public String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override
    public String getDeviceModel() {
        return Build.MANUFACTURER+" "+Build.MODEL;
    }

    //Android ID
    @Override
    public String getUniqueID() {
        return Settings.Secure.ANDROID_ID;
    }

    //Role: Hardware tracking
    @Override
    public String getBuildSerial() {
        //Build.getSerial();
        return Build.SERIAL;
    }

    //Role: Hardware tracking
    @Override
    public String getSIMSerial() {
        return telephonyManager.getSimSerialNumber();
    }

    //Role: Hardware tracking
    @Override
    public String getIMEI() {
        return telephonyManager.getDeviceId();
    }

}
