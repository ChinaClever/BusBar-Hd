package com.clever.www.busbar.net.udp.recv;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Lzy on 17-7-27.
 */

public class UdpRecvSocket {
    private static final int MAX_SIZE = 1024;
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    private byte[] data = new byte[MAX_SIZE]; // 创建字节数组，指定接收的数据包的大小

    public boolean init(int port) {
        boolean ret = true;
        try {
            socket = new DatagramSocket(port);  // 1.创建服务器端DatagramSocket，指定端口
            packet = new DatagramPacket(data, data.length);  // 2.创建数据报，用于接收客户端发送的数据

        } catch (SocketException e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    private boolean recv() {
        boolean ret = false;
        try {
            if(packet != null) {
                socket.receive(packet);// 3.接收客户端发送的数据 此方法在接收到数据报之前会一直阻塞
                ret = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            ret = false;
        }
        return  ret;
    }


    public int  readData(StringBuilder ip,  byte[] array) {
        int len =  0;
        if(recv()) {
            ip.append(packet.getAddress().toString());
            len = packet.getLength();
            for (int i = 0; i < len; ++i) {
                array[i] = data[i] ;
            }
        }

        return  len;
    }



}
