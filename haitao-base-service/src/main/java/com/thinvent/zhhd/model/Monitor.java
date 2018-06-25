package com.thinvent.zhhd.model;

import java.util.List;


public abstract class Monitor {
    public abstract List<IDevice> getDeviceList();
    public abstract void setDeviceList(List<IDevice> deviceList);
    public abstract IDevice getDeviceById(String deviceId);
    public abstract void addDevice(IDevice device);
    public abstract void removeDeviceById(String deviceId);
    public abstract void clearDeviceList();
}