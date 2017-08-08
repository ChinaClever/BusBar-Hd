package com.clever.www.busbar.box;

/**
 * Created by Lzy on 17-8-8.
 */

public class BoxItem {

    private int id = 0;
    private String name = "";
    private double vol=-1;
    private double cur=-1;
    private double ele =-1;
    private double pf = -1;

    public BoxItem(int id){
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public double getCur() {
        return cur;
    }

    public void setCur(double cur) {
        this.cur = cur;
    }

    public double getEle() {
        return ele;
    }

    public void setEle(double ele) {
        this.ele = ele;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

}
