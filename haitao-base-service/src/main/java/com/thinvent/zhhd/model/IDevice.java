package com.thinvent.zhhd.model;


public interface IDevice {
    public String getDeviceId();
    public void setDeviceId(String deviceId);

    public String getMpId();
    public void setMpId(String mpId);

    public String getUsage();
    public void setUsage(String usage);

    public Double getLng();
    public void setLng(Double lng);

    public Double getLat();
    public void setLat(Double lat);

    public String getMerchant();
    public void setMerchant(String merchant);

    public String getDescription();
    public void setDescription(String description);
}