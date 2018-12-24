package com.clever.www.busbar.boxline;

/**
 * Created by Lzy on 17-8-2.
 */

public class BoxLineItem {

    private int id = 0;
    private String name = "";
    private double vol=-1;
    private double cur=-1;
    private double pl = 0;
    private double curThd = 0;

    private double pow = 0;
    private double pf = 0;
    private double ele = 0;
    private double tem = 0;

    public BoxLineItem(int id){
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

    public double getPl() {
        return pl;
    }

    public void setPl(double cur) {
        this.pl = cur;
    }

    public double getCurThd() {
        return curThd;
    }

    public void setCurThd(double maxCur) {
        this.curThd = maxCur;
    }

    public double getPow() {
        return pow;
    }

    public void setPow(double pow) {
        this.pow = pow;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public double getEle() {
        return ele;
    }

    public void setEle(double ele) {
        this.ele = ele;
    }


    public double getTem() {
        return tem;
    }

    public void setTem(double tem) {
        this.tem = tem;
    }
}
