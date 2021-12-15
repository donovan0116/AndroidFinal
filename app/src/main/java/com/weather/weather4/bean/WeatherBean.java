package com.weather.weather4.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherBean implements Serializable {

    @SerializedName("cityid")
    private String cityId;

    @SerializedName("city")
    private String city;

    @SerializedName("update_time")
    private String updateTime;

    @SerializedName("data")
    private List<DayWeatherBean> dayWeatherBeanList;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<DayWeatherBean> getDayWeatherBeanList() {
        return dayWeatherBeanList;
    }

    public void setDayWeatherBeanList(List<DayWeatherBean> dayWeatherBeanList) {
        this.dayWeatherBeanList = dayWeatherBeanList;
    }

    @Override
    public String toString() {
        return "weatherBean{" +
                "cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", dayWeatherBeanList=" + dayWeatherBeanList +
                '}';
    }
}
