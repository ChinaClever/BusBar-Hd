package com.clever.www.busbar.setting.setline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clever.www.busbar.R;

public class SetLineActivity extends AppCompatActivity {
    private SetLineCst mSetLineCst_1, mSetLineCst_2, mSetLineCst_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_line_activity);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.set_line_activity_tile);
        }

        mSetLineCst_1 = (SetLineCst) findViewById(R.id.set_line_1);
        mSetLineCst_1.setLine(0);

        mSetLineCst_2 = (SetLineCst) findViewById(R.id.set_line_2);
        mSetLineCst_2.setLine(1);

        mSetLineCst_3 = (SetLineCst) findViewById(R.id.set_line_3);
        mSetLineCst_3.setLine(2);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetLineActivity.class);
        context.startActivity(intent);
    }
}
