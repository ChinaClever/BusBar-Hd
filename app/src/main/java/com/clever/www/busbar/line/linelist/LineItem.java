package com.clever.www.busbar.line.linelist;

/**
 * Created by Lzy on 17-8-4.
 */

public class LineItem {
    private int id = 0;
    private String name;
    private int vol = 0;
    private int volALarm=0, volCrALarm=0;
    private double cur = 0;
    private int curALarm=0, curCrALarm=0;

    private double pl = 0;
    private double curThd = 0;
    private double volThd = 0;

    private double pow = 0;
    private double pf = 0;
    private double ele = 0;
    private double tem = 0;
    private int temALarm=0, temCrALarm=0;

    public LineItem(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
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

    public double getVolThd() {
        return volThd;
    }

    public void setVolThd(double maxCur) {
        this.volThd = maxCur;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
