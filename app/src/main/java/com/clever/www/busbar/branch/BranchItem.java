package com.clever.www.busbar.branch;

/**
 * Created by Lzy on 17-8-4.
 */

public class BranchItem {
    private int id=0;
    private String name;
    private int status = 0;
    private int vol = -1;
    private double cur = -1;
    private double pow = -1;
    private double apPow = -1;
    private double pf = -1;
    private double ele = -1;
    private int tem = -1;

    public BranchItem(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public double getPow() {
        return pow;
    }

    public void setPow(double pow) {
        this.pow = pow;
    }

    public double getApPow() {
        return apPow;
    }

    public void setApPow(double apPow) {
        this.apPow = apPow;
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

    public int getTem() {
        return tem;
    }

    public void setTem(int tem) {
        this.tem = tem;
    }

}
