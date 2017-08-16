package com.clever.www.busbar.setting.setbox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.clever.www.busbar.R;

import java.util.ArrayList;
import java.util.List;

public class SetBoxActivity extends AppCompatActivity {
    private List<SetBoxItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_box_activity);

        initItemss();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SetBoxAdapter adapter = new SetBoxAdapter(mItems);
        recyclerView.setAdapter(adapter);

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetBoxActivity.class);

        context.startActivity(intent);
    }

    private void initItemss() {
        for(int i=0; i<3; ++i) {
            SetBoxItem item = new SetBoxItem(i);
            mItems.add(item);
        }
    }
}
