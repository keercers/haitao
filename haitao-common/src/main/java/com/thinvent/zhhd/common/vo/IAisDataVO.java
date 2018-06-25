package com.thinvent.zhhd.common.vo;

import java.util.Date;

public interface IAisDataVO {
    public String getAisId();

    public void setAisId(String aisId);

    public String getCnName();

    public void setCnName(String cnName);

    public String getEnName();

    public void setEnName(String enName);

    public String getCallNo();

    public void setCallNo(String callNo);

    public String getMmsi();

    public void setMmsi(String mmsi);

    public String getImo();

    public void setImo(String imo);

    public String getShipCategory();

    public void setShipCategory(String shipCategory);

    public String getDeviceType();

    public void setDeviceType(String deviceType);

    public Float getShipLength();

    public void setShipLength(Float shipLength);

    public Float getShipWidth();

    public void setShipWidth(Float shipWidth);

    public Float getDraugth();

    public void setDraugth(Float draugth);

    public Double getLng();

    public void setLng(Double lng);

    public Double getLat();

    public void setLat(Double lat);

    public Float getShipCourseDirection();

    public void setShipCourseDirection(Float shipCourseDirection);

    public Float getSpeed();

    public void setSpeed(Float speed);

    public String getStatus();

    public void setStatus(String status);

    public String getPort();

    public void setPort(String port);

    public Date getGpsTime();

    public void setGpsTime(Date gpsTime);
    
    public Double getUserX();

    public void setUserX(Double userX);

    public Double getUserY();

    public void setUserY(Double userY);

    public Float getShipHeading();

    public void setShipHeading(Float shipHeading);

    public Float getShipTurnRate();

    public void setShipTurnRate(Float shipTurnRate);
	
	public Date getCreateTime();

    public void setCreateTime(Date createTime);
}

