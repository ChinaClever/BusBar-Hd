package com.clever.www.busbar;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.clever.www.busbar.box.BoxFragment;
import com.clever.www.busbar.dp.dev.DevSpiedThread;
import com.clever.www.busbar.net.NetRecvThread;

public class MainActivity extends AppCompatActivity {
    private NetRecvThread mNetRecvThread = new NetRecvThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFun();
    }

    private void initFun() {
        mNetRecvThread.initNet();
        DevSpiedThread.get().startThread();
    }

    public void changedFragment(int id) {
        Toast.makeText(this, "lzy: " + id, Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new BoxFragment());
        transaction.commit();
    }

}
