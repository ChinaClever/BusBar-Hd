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

public class SetMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_menu_activity);

        Button button = findViewById(R.id.line_btn);
        button.setOnClickListener(onClickListener);

        button = findViewById(R.id.branch_btn);
        button.setOnClickListener(onClickListener);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetMenuActivity.class);
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

    private void resultActivity(int id) {
        Intent intent = new Intent();
        intent.putExtra("set_menu", id);
        setResult(RESULT_OK, intent);
        finish();
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.line_btn:
                    if(inputCheck()) {
                        resultActivity(1);
                    }
                    break;

                case R.id.branch_btn:
                    if(inputCheck()) {
                        resultActivity(2);
                    }
                    break;
            }
        }
    };



}
