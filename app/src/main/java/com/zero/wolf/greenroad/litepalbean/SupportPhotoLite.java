package com.zero.wolf.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/7/3.
 */

public class SupportPhotoLite extends DataSupport {


    private String shutTime;

    public String getShutTime() {
        return shutTime;
    }

    public void setShutTime(String shutTime) {
        this.shutTime = shutTime;
    }

    private String car_type;
    private String photoPath1;
    private String photoPath2;
    private String photoPath3;
    private String license_color;
    private String username;
    private String operator;
    private String station;
    private String license_plate;
    private String goods;
    private int isPost;//1 为上传成功   0 为上传失败或未上传

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getIsPost() {
        return isPost;
    }

    public void setIsPost(int isPost) {
        this.isPost = isPost;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }


    public String getPhotoPath1() {
        return photoPath1;
    }

    public void setPhotoPath1(String photoPath1) {
        this.photoPath1 = photoPath1;
    }

    public String getPhotoPath2() {
        return photoPath2;
    }

    public void setPhotoPath2(String photoPath2) {
        this.photoPath2 = photoPath2;
    }

    public String getPhotoPath3() {
        return photoPath3;
    }

    public void setPhotoPath3(String photoPath3) {
        this.photoPath3 = photoPath3;
    }

    public String getLicense_color() {
        return license_color;
    }

    public void setLicense_color(String license_color) {
        this.license_color = license_color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}