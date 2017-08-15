package com.clever.www.busbar.setting.setcom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.dp.data.packages.devdata.DevDataUnit;
import com.clever.www.busbar.login.LoginStatus;

public class SetComActivity extends Activity {
    private int boxNUm=0, modeId = 0, mLine=0;
    private int mRate = 10;
    private EditText minEt, maxEt, crMinEt, crMaxEt;

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
        DevDataPacket dataPacket = LoginStatus.getPacket(boxNUm);
        DevDataUnit dataUnit = null;

        switch (modeId) {
            case 1:
                dataUnit = dataPacket.data.line.cur;
                break;
            case 2:
                dataUnit = dataPacket.data.line.vol;
                mRate = 1;
                break;
            default:
                break;
        }

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

    public static void actionStart(Context context, int boxNUm, int mode) {
        Intent intent = new Intent(context, SetComActivity.class);
        intent.putExtra("box_num", boxNUm);
        intent.putExtra("set_mode", mode);
        context.startActivity(intent);
    }

    private void close() {
        this.finish();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.save_btn:
                    ///============== 保存 数据



                    break;

                case R.id.cancel_btn:
                    close();
                    break;
            }
        }
    };

}
