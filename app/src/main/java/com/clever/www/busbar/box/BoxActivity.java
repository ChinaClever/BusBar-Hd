package com.clever.www.busbar.box;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.dp.data.packages.DevDataPacket;
import com.clever.www.busbar.login.LoginStatus;

import java.util.ArrayList;
import java.util.List;

public class BoxActivity extends AppCompatActivity {
    private List<BoxItem> boxItems = new ArrayList<>();
    private BoxUpdate mBoxUpdate = null;
    private int mBOxID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_activity);

        Intent intent = getIntent();
        mBOxID = intent.getIntExtra("box_id", 0);
        initBOxName(mBOxID);

        BoxTotalCst totalCst = (BoxTotalCst) findViewById(R.id.total_cst);
        totalCst.setBoxId(mBOxID);

        initRecyclerView();
    }

    public static void actionStart(Context context, int boxId) {
        Intent intent = new Intent(context, BoxActivity.class);
        intent.putExtra("box_id", boxId);
        context.startActivity(intent);
    }

    private void initRecyclerView() {
        initBoxItem();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        BoxAdapter adapter = new BoxAdapter(boxItems);
        recyclerView.setAdapter(adapter);

        mBoxUpdate = new BoxUpdate();
        mBoxUpdate.setData(adapter, boxItems, mBOxID);
    }

    private void initBoxItem() {
        for(int i=0; i<3; ++i) {
            BoxItem item = new BoxItem(i);
            boxItems.add(item);
        }
    }

    private void initBOxName(int id) {
        DevDataPacket packet = LoginStatus.getPacket(id);

        String name = packet.devInfo.name.get();
        if(name.isEmpty()) name = " iBox-" + id;

        TextView tv = (TextView) findViewById(R.id.name_tv);
        tv.setText(name);
    }

}
