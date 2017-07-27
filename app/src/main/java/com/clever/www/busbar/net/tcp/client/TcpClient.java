package com.clever.www.busbar.net.tcp.client;

import java.io.IOException;

/**
 * Created by Lzy on 17-7-27.
 */

public class TcpClient {
    private static final int TCP_PORT = TcpEnum.TCP_PORT.getValue(); // TCP 通讯的端口号
    private TcpClientSocket mTcpClient = TcpClientSocket.bulid();
    private static TcpClient mClient = null; // 唯一对象
    public int port=-1;

    /**
     * 获取唯一对象
     * @return
     */
    public static TcpClient bulid() {
        if(mClient == null)
            mClient = new TcpClient();
        return mClient;
    }


    /**
     * 连接服务端
     * @param  ip 目标地址
     * @return true 连接成功
     */
    public boolean connect(String ip, int port) {
        boolean ret = true;
        try {
            mTcpClient.connectServer(ip, port);
            this.port = port;
        } catch (IOException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }


    public boolean connect(String ip) {
        return  connect(ip, TCP_PORT);
    }

    /**
     * 发送数据
     * @param data 数据
     * @param len 数据长度
     * @return true 发送成功
     */
    public boolean sent(byte[] data, int len){
        boolean ret = true;
        try {
            mTcpClient.sentData(data, len);
        } catch (IOException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

    /**
     * 关闭套接字
     */
    public void close(){
        mTcpClient.closeSocket();
    }

    /**
     *  接收数据
     * @param data 数据
     * @return 长度
     */
    public int recv(byte[] data) {
        return mTcpClient.recvData(data);
    }

    /**
     * 获取服务端IP地址
     * @return IP
     */
    public String getServerIp() {
        return mTcpClient.getServerIP();
    }

    /**
     * 是否连接
     * @return true
     */
    public boolean isConnect() {
        return mTcpClient.isConnect();
    }
}
