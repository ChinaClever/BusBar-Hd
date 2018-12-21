package com.clever.www.busbar.branch;

/**
 * Created by Lzy on 17-8-4.
 */

public class BranchItem {
    private int id=0;
    private String name;
    private int status = 0;
    private double[] cur = new double[3];
    private double ele[] = new double[3];

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

    public double getCur(int i) {
        return cur[i];
    }

    public void setCur(int i, double cur) {
        this.cur[i] = cur;
    }

    public double getEle(int i) {
        return ele[i];
    }

    public void setEle(int i, double ele) {
        this.ele[i] = ele;
    }

}
