package com.clever.www.busbar.home;

/**
 * Created by Lzy on 17-8-4.
 */

public class HomeItem {
    public   DataItem dataItem[] = new DataItem[2];

    public HomeItem(int id) {
        dataItem[0].setId(id);
        dataItem[1].setId(id+1);
    }


     class DataItem {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getCur() {
            return cur;
        }

        public void setCur(double cur) {
            this.cur = cur;
        }

        public int getAlarm() {
            return alarm;
        }

        public void setAlarm(int alarm) {
            this.alarm = alarm;
        }

        private int id=0;
        private double cur = -1;
        private int alarm = 0;
    }


}
