package com.clever.www.busbar.setting.setTem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.clever.www.busbar.R;

import java.util.ArrayList;
import java.util.List;

public class SetTemActivity extends AppCompatActivity {
    private List<SetTemItem> mItems = new ArrayList<>();
    private SetTemUpdate mTemUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_tem_activity);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.set_tem_activity_tile);
        }

        initItems();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SetTemAdapter adapter = new SetTemAdapter(mItems);
        recyclerView.setAdapter(adapter);

        mTemUpdate = new SetTemUpdate();
        mTemUpdate.setData(adapter, mItems);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetTemActivity.class);

        context.startActivity(intent);
    }


    private void initItems() {
        for(int i=0; i<3; ++i) {
            SetTemItem item = new SetTemItem(i);
            mItems.add(item);
        }
    }

}
