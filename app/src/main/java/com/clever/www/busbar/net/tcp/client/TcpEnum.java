package com.clever.www.busbar.net.tcp.client;

/**
 * Created by Lzy on 17-7-27.
 */

public enum TcpEnum {
    TCP_PORT(11283); // TCP连接端口

    private int value;
    private TcpEnum(int value) { this.value = value; }
    public int getValue() {  return value; }
}
