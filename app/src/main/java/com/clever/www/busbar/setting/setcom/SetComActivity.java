package com.clever.www.busbar.setting.setcom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.clever.www.busbar.R;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.dp.data.packages.base.DevDataBase;
import com.clever.www.busbar.dp.data.packages.devdata.DevDataUnit;
import com.clever.www.busbar.login.LoginStatus;
import com.clever.www.busbar.net.data.packages.NetDataDomain;

import java.util.ArrayList;
import java.util.List;

public class SetComActivity extends Activity {
    private int boxNUm=0, modeId = 0, mLine=0;
    private int mRate = 10;
    private EditText minEt, maxEt, crMinEt, crMaxEt;
    private DevDataPacket dataPacket = LoginStatus.getPacket(boxNUm);
    private DevDataUnit dataUnit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_com_activity);

        Intent intent = getIntent();
        boxNUm  = intent.getIntExtra("box_num", 0);
        modeId  = intent.getIntExtra("set_mode", 0);

        Button button = findViewById(R.id.save_btn);
        button.setOnClickListener(onClickListener);

        button = findViewById(R.id.cancel_btn);
        button.setOnClickListener(onClickListener);

        initView();
        initTitle();
    }


    private void initTitle() {
        String str = "";

        if(boxNUm == 0) {
            str = "始端箱";
        } else {
            str = "接插箱 ";

        }

        DevDataPacket dataPacket = LoginStatus.getPacket(boxNUm);
        String name = dataPacket.devInfo.name.get();
        if(name.isEmpty())
            name = "iBox-" + (boxNUm+1);
        str += name + " L" + (mLine+1);

        switch (modeId) {
            case 1:
                str += "相电流";
                break;

            case 2:
                str += "相电压";
                break;
        }
        str += "阈值设置";

        TextView tv = findViewById(R.id.title_tv);
        tv.setText(str);
    }

    private void initView() {
        String unitSym = "A";

        switch (modeId) {
            case 1:
                unitSym = "A";
                dataUnit = dataPacket.data.line.cur;
                break;
            case 2:
                unitSym = "V";
                dataUnit = dataPacket.data.line.vol;
                mRate = 1;
                break;
            default:
                break;
        }
        initUnitSym(unitSym);

        minEt = findViewById(R.id.min_et);
        String str = dataUnit.min.get(mLine) / mRate +"";
        minEt.setText(str);

        maxEt = findViewById(R.id.max_et);
        str = dataUnit.max.get(mLine) / mRate +"";
        maxEt.setText(str);

        crMinEt = findViewById(R.id.crmin_et);
        str = dataUnit.crMin.get(mLine) / mRate +"";
        crMinEt.setText(str);

        crMaxEt = findViewById(R.id.crmax_et);
        str = dataUnit.crMax.get(mLine) / mRate +"";
        crMaxEt.setText(str);
    }

    private void initUnitSym(String str) {
        EditText et = findViewById(R.id.unit_sym_1);
        et.setText(str);

        et = findViewById(R.id.unit_sym_2);
        et.setText(str);

        et = findViewById(R.id.unit_sym_3);
        et.setText(str);

        et = findViewById(R.id.unit_sym_4);
        et.setText(str);
    }




    public static void actionStart(Context context, int boxNUm, int mode) {
        Intent intent = new Intent(context, SetComActivity.class);
        intent.putExtra("box_num", boxNUm);
        intent.putExtra("set_mode", mode);
        context.startActivity(intent);
    }

    private void close() {
        this.finish();
    }

    /**
     * 设置位数
     * @return 0 统一设置
     */
    private byte getBit() {
        byte id = (byte) (mLine +1);
        CheckBox box = (CheckBox) findViewById(R.id.checkBox);
        if(box.isChecked())
            id = 0;
        return id;
    }

    private boolean sendData(int min, int max, int crMin, int crMax) {
        SetDevCom setDevCom = SetDevCom.get();

        List<Integer> list = new ArrayList<>();
        list.add(min);
        list.add(max);
        list.add(crMin);
        list.add(crMax);

        NetDataDomain pkt = new NetDataDomain();
        pkt.fn[0] = 0x71;
        pkt.fn[1] = getBit();
        pkt.len = setDevCom.intToByteList(list, pkt.data);

        return setDevCom.setDevData(pkt, false);
    }

    /**
     * 获取控件的值
     * @return -1 值为空
     */
    private int getEtView(EditText tv) {
        int data = 0;
        String str = tv.getText().toString();
        if ((str != null) && (str.length() > 0)) {

            str = str.replace("A","");
            str = str.replace("---","-1");

            double temp = Double.parseDouble(str);
            if(temp > 0)
                data = (int) (temp * mRate);
        }

        return data;
    }


    private String checkData(int min, int max, int crMin, int crMax) {
        String str = "";
        if(max > 100*mRate) {
            str = getResources().getString(R.string.set_ret_max);
        }

        if(min > max) {
            str = getResources().getString(R.string.set_ret_min);
        }

        if(crMin < min) {
            str = getResources().getString(R.string.set_ret_crmin);
        }

        if(crMax > max) {
            str = getResources().getString(R.string.set_ret_crmax);
        }

        if(crMin > crMax) {
            str = getResources().getString(R.string.set_ret_crminmax);
        }

        return str;
    }

    private boolean checkThreshold() {
        int min = getEtView(minEt);
        int max = getEtView(maxEt);
        int crMin = getEtView(crMinEt);
        int crMax = getEtView(crMaxEt);

        boolean ret = true;
        String str = checkData(min, max, crMin, crMax);
        if(str.length() > 0) {
            ret = false;
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }

        return ret;
    }

    private int setValue(EditText tv, DevDataBase dataBase) {
        int data = getEtView(tv);
        if(data >= 0)
            dataBase.set(mLine, data);
        return data;
    }

    /**
     * 阈值保存
     */
    private boolean saveThreshold() {
        int min = setValue(minEt, dataUnit.min);
        int max = setValue(maxEt, dataUnit.max);
        int crMin = setValue(crMinEt, dataUnit.crMin);
        int crMax = setValue(crMaxEt, dataUnit.crMax);

        int str = 0;
        boolean ret = sendData(min, max, crMin, crMax);
        if(ret) {
            str = R.string.set_save_ok;
        } else {
            str = R.string.set_save_err;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        return ret;
    }

    private void saveData() {
        boolean  ret = checkThreshold();
        if(ret) {
            saveThreshold();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.save_btn:
                    saveData();
                    break;

                case R.id.cancel_btn:
                    close();
                    break;
            }
        }
    };

}
