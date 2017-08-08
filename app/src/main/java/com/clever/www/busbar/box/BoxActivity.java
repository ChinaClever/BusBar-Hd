package com.clever.www.busbar.box;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.clever.www.busbar.R;

public class BoxActivity extends AppCompatActivity {
    private int mBOxID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_activity);

        Intent intent = getIntent();
        mBOxID = intent.getIntExtra("box_id", 0);

        BoxTotalCst totalCst = (BoxTotalCst) findViewById(R.id.total_cst);
        totalCst.setBoxId(mBOxID);

    }

    public static void actionStart(Context context, int boxId) {
        Intent intent = new Intent(context, BoxActivity.class);
        intent.putExtra("box_id", boxId);
        context.startActivity(intent);
    }




}
