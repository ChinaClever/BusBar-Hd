package com.clever.www.busbar.box;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.clever.www.busbar.R;

public class BxxActivity extends AppCompatActivity {
    private int mBOxID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bxx_activity);

        Intent intent = getIntent();
        mBOxID = intent.getIntExtra("box_id", 0);



    }

    public static void actionStart(Context context, int boxId) {
        Intent intent = new Intent(context, BxxActivity.class);
        intent.putExtra("box_id", boxId);
        context.startActivity(intent);
    }
}
