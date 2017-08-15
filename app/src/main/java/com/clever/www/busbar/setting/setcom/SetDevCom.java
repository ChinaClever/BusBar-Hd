package com.clever.www.busbar.setting.setcom;

import com.clever.www.busbar.common.array.ArrayUtil;
import com.clever.www.busbar.login.LoginStatus;
import com.clever.www.busbar.net.data.datadone.NetPackData;
import com.clever.www.busbar.net.data.packages.NetDataDomain;
import com.clever.www.busbar.net.tcp.client.TcpSingle;
import com.clever.www.busbar.net.udp.sent.UdpSend;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: lzy. Created on: 16-11-18.
 */

public class SetDevCom {
    private NetPackData mNetPackData = new NetPackData();
    private UdpSend mUdpSend = new UdpSend();
    private List<byte[]> mTcpSentListBuf = new LinkedList<>();
    private List<byte[]> mUdpSentListBuf = new LinkedList<>();
    private static SetDevCom mSingle = null;

    private SetDevCom() {
        startThread();
    }

    public static SetDevCom get() {
        if(mSingle == null)
            mSingle = new SetDevCom();
        return mSingle;
    }

    public int toByteList(List<Integer> list, List<Byte> data) {
        for(int i=0; i<list.size(); ++i) {
            int value = list.get(i);
            data.add((byte)value);
        }
        return list.size();
    }

    public int intToByteList(List<Integer> list, List<Byte> data) {
        for(int i=0; i<list.size(); ++i) {
            int value = list.get(i) >> 8;
            data.add((byte)value);

            value = list.get(i) & 0xff;
            data.add((byte)value);
        }
        return list.size()*2;
    }

    public int stringToByteList(String str, List<Byte> data) {
        byte[] bytes = str.getBytes();
        for(int i=0; i<bytes.length; ++i) {
            data.add(bytes[i]);
        }
        return data.size();
    }

    private void addSentBuf(byte[] buf, int len, boolean udpMode) {
        byte[] array = ArrayUtil.copyAarry(buf, len);
        if(udpMode) {
            mUdpSentListBuf.add(array);
        } else {
            mTcpSentListBuf.add(array);
        }
    }

    /**
     * 设置设备数据
     * @param pkt
     * @param udpMode
     * @return
     */
    public boolean setDevData(NetDataDomain pkt , boolean udpMode) {
        DevDataPacket dataPacket = LoginStatus.getPacket(0);
        if(dataPacket != null) {
            int num = 1;  //////=====  设备类型
            pkt.addr = (byte) LoginStatus.login_devNum;
            byte[] buf = new byte[256];
            int len = 0;

            if (udpMode) {
                len = mNetPackData.getUdpPackets(num, pkt, buf);
            } else {
                len = mNetPackData.getTcpPackets(num, pkt, buf);
            }
            addSentBuf(buf, len, udpMode);
        }

        return TcpSingle.bulid().isConnect();
    }


    private void sentData() {
        if(mUdpSentListBuf.size() > 0) {
            byte[] buf = mUdpSentListBuf.get(0);
            mUdpSend.dbSent(buf, buf.length);
            mUdpSentListBuf.remove(0);
        }

        if(mTcpSentListBuf.size() > 0) {
            byte[] buf = mTcpSentListBuf.get(0);
            TcpSingle.bulid().sent(buf, buf.length);

            ///////====================
            //// 特别说明，MPDU只能通过UDP的形式接收数据
            String ip = TcpSingle.bulid().getServerIp().replaceAll("/","");
            mUdpSend.sent(ip, 18750, buf, buf.length);


//            mUdpSend.sent("192.168.1.100", 18750, buf, buf.length);
//            Log.d("LZY", "setLine: " + ip);
            /////////////////////////////////////////

            mTcpSentListBuf.remove(0);
        }
    }


    private void startThread() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1*1000);
                        sentData();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }


}
