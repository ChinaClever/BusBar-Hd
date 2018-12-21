package com.clever.www.busbar.box;

/**
 * Created by Lzy on 17-8-8.
 */

public class BoxItem {

    private int id = 0;
    private String line = "";
    private String name = "";
    private double vol=-1;
    private int volALarm=0, volCrALarm=0;

    private double cur=-1;
    private int curALarm=0, curCrALarm=0;
    private double ele =-1;
    private double pf = -1;
    private double pow = -1;
    private double appow = -1;

    private double tem = -1;
    private int temALarm=0, temCrALarm=0;
    private int sw = -1;

    public BoxItem(int id){
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLine() {
        return line;
    }
    public void setLine(String name) { this.line = name; }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

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

    public double getPow() {
        return pow;
    }

    public void setPow(double pow) {
        this.pow = pow;
    }

    public double getAppow() {
        return appow;
    }

    public void setAppow(double appow) {
        this.appow = appow;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public double getTem() {
        return tem;
    }

    public void setTem(double tem) {
        this.tem = tem;
    }

    public int getVolALarm() {
        return volALarm;
    }

    public void setVolALarm(int volALarm) {
        this.volALarm = volALarm;
    }

    public int getVolCrALarm() {
        return volCrALarm;
    }

    public void setVolCrALarm(int volCrALarm) {
        this.volCrALarm = volCrALarm;
    }

    public int getCurALarm() {
        return curALarm;
    }

    public void setCurALarm(int curALarm) {
        this.curALarm = curALarm;
    }

    public int getCurCrALarm() {
        return curCrALarm;
    }

    public void setCurCrALarm(int curCrALarm) {
        this.curCrALarm = curCrALarm;
    }

    public int getTemALarm() {
        return temALarm;
    }

    public void setTemALarm(int temALarm) {
        this.temALarm = temALarm;
    }

    public int getTemCrALarm() {
        return temCrALarm;
    }

    public void setTemCrALarm(int temCrALarm) {
        this.temCrALarm = temCrALarm;
    }
}
