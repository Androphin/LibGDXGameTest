package com.orbit.mygame;


public interface DeviceData {

    public String getUniqueID();
    public String getBuildSerial();
    public String getSIMSerial();
    public String getIMEI();

    public String getCountryCode();
    public String getOSVersion();
    public String getDeviceModel();
}
