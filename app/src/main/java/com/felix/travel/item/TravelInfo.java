package com.felix.travel.item;

/**
 * Created by felixlin on 2016/12/7.
 */
public class TravelInfo {

    private String areaName;
    private String areaInfo;

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "TravelInfo{" +
                "areaName='" + areaName + '\'' +
                ", areaInfo='" + areaInfo + '\'' +
                '}';
    }
}
