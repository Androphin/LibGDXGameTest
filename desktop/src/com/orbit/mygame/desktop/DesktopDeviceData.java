package com.orbit.mygame.desktop;

import com.orbit.mygame.DeviceData;

import java.util.Locale;

/**
 * Created by Korbi on 08.03.2018.
 */

public class DesktopDeviceData implements DeviceData {

    @Override
    public String getCountryCode() {
        return Locale.getDefault().getISO3Country();
    }

    @Override
    public String getOSVersion() {
        return System.getProperty("os.name")+" "+System.getProperty("os.version");
    }

    @Override
    public String getDeviceModel() {
        return "DESKTOP_DEVICEMODEL";
    }

    @Override
    public String getUniqueID() {
        return "DESKTOP_UNIQUE_ID";
    }

    @Override
    public String getBuildSerial() {
        return "DESKTOP_build_serial";
    }

    @Override
    public String getSIMSerial() {
        return "DESKTOP_sim_serial";
    }

    @Override
    public String getIMEI() {
        return "DESKTOP_IMEI";
    }


}
