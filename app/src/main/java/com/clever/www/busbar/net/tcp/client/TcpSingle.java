package com.clever.www.busbar.net.tcp.client;



/**
 * Author: lzy. Created on: 16-11-4.
 * TCP 单态类
 * 提供 连接、断开、发送·接口
 */
public class TcpSingle {
    private TcpClient mTcpClient = TcpClient.bulid();
    private TcpRecvThread mRecvThread = TcpRecvThread.get();
    private static TcpSingle single = null;

    private TcpSingle() {
        new Thread(mRecvThread).start();
        stopThread(); // 暂停接收
    }

    public void startThread() {
        mRecvThread.setRun(true);
    }

    public void stopThread() {
        mRecvThread.setRun(false);
    }

    /**
     * 获取单态接口
     */
    public static TcpSingle bulid() {
        if(single == null) {
            single = new TcpSingle();
        }
        return single;
    }

    public TcpClient getTcpClient() { return mTcpClient; } /* 获取客户商套接字 */
    public void connect(final String ip, final int port) {
        new Thread() {
            @Override
            public void run() {
                mTcpClient.connect(ip,port);
            }
        }.start();
    }

    public void connect(String ip) { mTcpClient.connect(ip); }

    public void sent(final byte[] data, final int len) {
        if(isConnect()) {
            new Thread() {
                @Override
                public void run() {
                    mTcpClient.sent(data, len);
                }
            }.start();
        }
    }
    public boolean isConnect() { return mTcpClient.isConnect(); }
    public String getServerIp() { return mTcpClient.getServerIp(); }
}
