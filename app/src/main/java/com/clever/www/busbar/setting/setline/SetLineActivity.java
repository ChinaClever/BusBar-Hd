package com.clever.www.busbar.setting.setline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clever.www.busbar.R;

public class SetLineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_line_activity);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetLineActivity.class);
        context.startActivity(intent);
    }
}
