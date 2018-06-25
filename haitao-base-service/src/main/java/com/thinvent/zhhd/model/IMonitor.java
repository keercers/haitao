package com.thinvent.zhhd.model;


public interface IMonitor {
    public String getMonitorId();
    public void setMonitorId(String monitorId);

    public String getMonitorName();
    public void setMonitorName(String monitorName);

    public String getMonitorLocation();
    public void setMonitorLocation(String monitorLocation);

    public String getMonitorType();
    public void setMonitorType(String monitorType);

    public String getSpecialMark();
    public void setSpecialMark(String specialMark);
}