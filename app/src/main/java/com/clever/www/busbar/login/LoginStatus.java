package com.clever.www.busbar.login;


import com.clever.www.busbar.dp.data.hash.read.BusHashRead;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.net.tcp.client.TcpSingle;

/**
 * Author: lzy. Created on: 16-11-3.
 */
public class LoginStatus {
    public static boolean isLogin = false; // 默认为非连接
    public static String login_ip;
    public static int login_devNum = 0;
    public static String login_usr;
    public static String login_pwd;
    public static boolean isRemember = false;

    private static BusHashRead mReadHash = new BusHashRead();
    public static DevDataPacket getPacket(int num) {
        DevDataPacket dataPacket = mReadHash.get(login_devNum, num);;
        if(!isLogin) {
            if(dataPacket != null)
                dataPacket.offLine = 0;
        }
        return dataPacket;
    }

    public static boolean getLogin() {
        if(isLogin) {
            if(!TcpSingle.bulid().isConnect())
                isLogin = false;

//            DevDataPacket dataPacket = getPacket(0);
//            if(dataPacket != null) {
//                if(dataPacket.offLine == 0)
//                    isLogin = false;
//            } else {
//                isLogin = false;
//            }
        }

        return isLogin;
    }

}
