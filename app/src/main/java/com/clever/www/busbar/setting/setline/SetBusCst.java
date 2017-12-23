package com.clever.www.busbar.setting.setline;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.clever.www.busbar.R;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.login.LoginStatus;
import com.clever.www.busbar.net.data.packages.NetDataDomain;
import com.clever.www.busbar.setting.setcom.SetDevCom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lzy on 17-8-14.
 */

public class SetBusCst extends LinearLayout{
    private EditText mBusNameEt, mBusCurEt, mBusNumEt;
    private Context mContext;
    private DevDataPacket dataPacket = LoginStatus.getPacket(0);

    public SetBusCst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.set_bus_cst, this);

        mBusNameEt = view.findViewById(R.id.bus_name_et);
        mBusCurEt = view.findViewById(R.id.bus_cur_et);
        mBusNumEt = view.findViewById(R.id.bus_num_et);

        Button button = view.findViewById(R.id.saveBtn);
        button.setOnClickListener(onClickListener);

        initView();
    }

    private void initView() {

        String name = dataPacket.devInfo.name.get();
        mBusNameEt.setText(name);

        int curRate = dataPacket.rateCur / 10;
        String str =  curRate +"";
        mBusCurEt.setText(str);

        int num = dataPacket.boxSize;
        str = num + "";
        mBusNumEt.setText(str);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.saveBtn:
                    if(inputCheck()) {
                        saveInfo();
                    }
                    break;
            }
        }
    };

    private boolean inputCheck() {
        boolean ret = false;
        String name = mBusNameEt.getText().toString();
        if(name.isEmpty()) {
            Toast.makeText(mContext, R.string.set_bus_name_empty, Toast.LENGTH_SHORT).show();
            return ret;
        }

        String str  = mBusCurEt.getText().toString();
        if(str != null) {
            int value = (int)Double.parseDouble(str) * 10;
            if(value <= 0) {
                Toast.makeText(mContext, R.string.set_bus_cur_err, Toast.LENGTH_SHORT).show();
                return ret;
            }
        } else {
            Toast.makeText(mContext, R.string.set_bus_cur_err, Toast.LENGTH_SHORT).show();
            return ret;
        }

        str = mBusNumEt.getText().toString();
        if(str != null) {
            int value = Integer.parseInt(str);
            if(value <= 0) {
                Toast.makeText(mContext, R.string.set_bux_num_err, Toast.LENGTH_SHORT).show();
                return ret;
            }
        } else {
            Toast.makeText(mContext, R.string.set_bux_num_err, Toast.LENGTH_SHORT).show();
            return ret;
        }

        ret = true;

        return ret;
    }

    private boolean saveBusName() {
        String name = mBusNameEt.getText().toString();
        dataPacket.devInfo.name.set(name);

        SetDevCom setDevCom = SetDevCom.get();
        NetDataDomain pkt = new NetDataDomain();
        pkt.fn[0] = 5;
        pkt.fn[1] = 0x11;
        pkt.len = setDevCom.stringToByteList(name, pkt.data);

        boolean ret = setDevCom.setDevData(pkt, false);
        if(ret) {

        }

        return ret;
    }


    private boolean saveBusCur() {
        String str  = mBusCurEt.getText().toString();
        int value = (int)Double.parseDouble(str) * 10;

        List<Integer> list = new ArrayList<>();
        list.add(value);

        SetDevCom setDevCom = SetDevCom.get();
        NetDataDomain pkt = new NetDataDomain();
        pkt.fn[0] = 30;
        pkt.fn[1] = 0;

        pkt.len = setDevCom.intToByteList(list, pkt.data);
        return setDevCom.setDevData(pkt, false);
    }

    private boolean saveBoxNum() {
        String str  = mBusNumEt.getText().toString();
        int value = Integer.parseInt(str);

        SetDevCom setDevCom = SetDevCom.get();
        NetDataDomain pkt = new NetDataDomain();
        pkt.fn[0] = 31;
        pkt.fn[1] = 0;

        pkt.len = 1;
        pkt.data.add((byte)value);

        return setDevCom.setDevData(pkt, false);
    }


    private void saveInfo() {
        boolean ret = saveBusName();
        if(ret) {
            ret = saveBusCur();
            if(ret) {
                ret = saveBoxNum();
                if(ret) {
                    Toast.makeText(mContext, R.string.set_bus_save_ok, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
