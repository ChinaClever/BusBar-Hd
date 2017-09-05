package com.clever.www.busbar.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clever.www.busbar.R;
import com.clever.www.busbar.login.LoginStatus;
import com.clever.www.busbar.setting.setTem.SetTemActivity;
import com.clever.www.busbar.setting.setbox.SetBoxActivity;
import com.clever.www.busbar.setting.setline.SetLineActivity;

import java.util.ArrayList;
import java.util.List;

public class SetCheckPwdActivity extends Activity {
    List<Button> btns = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_checkpwd_activity);

        Button button = findViewById(R.id.line_Btn);
        btns.add(button);

        button = findViewById(R.id.branch_Btn);
        btns.add(button);

        button = findViewById(R.id.tem_Btn);
        btns.add(button);

        for(int i=0; i<btns.size(); ++i)
            btns.get(i).setOnClickListener(onClickListener);

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetCheckPwdActivity.class);
        context.startActivity(intent);
    }

    private boolean inputCheck() {
        boolean ret = false;

        String pwd = LoginStatus.login_pwd;
        if(!pwd.isEmpty()) {
            EditText et = findViewById(R.id.editText);
            String str = et.getText().toString();
            if (pwd.equals(str)) {
                ret = true;
            } else if (str.isEmpty()) {
                Toast.makeText(this, R.string.set_pwd_isempty, Toast.LENGTH_SHORT).show();
            } else  {
                Toast.makeText(this, R.string.set_pwd_err, Toast.LENGTH_SHORT).show();
            }
        } else {
            ret = true;
        }

        return ret;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.line_Btn:
                    if(inputCheck()) {
                        SetLineActivity.actionStart(SetCheckPwdActivity.this);
                    }
                    break;
                case R.id.branch_Btn:
                    if(inputCheck()) {
                        SetBoxActivity.actionStart(SetCheckPwdActivity.this);
                    }
                    break;
                case R.id.tem_Btn:
                    if(inputCheck()) {
                        SetTemActivity.actionStart(SetCheckPwdActivity.this);
                    }
                    break;
            }
//            finish();
        }
    };
}
