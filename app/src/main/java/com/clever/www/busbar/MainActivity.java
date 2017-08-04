package com.clever.www.busbar;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.clever.www.busbar.boxlist.BoxFragment;
import com.clever.www.busbar.branch.BranchFragment;
import com.clever.www.busbar.dp.dev.DevSpiedThread;
import com.clever.www.busbar.home.HomeFragment;
import com.clever.www.busbar.line.LineFragment;
import com.clever.www.busbar.net.NetRecvThread;

public class MainActivity extends AppCompatActivity {
    private NetRecvThread mNetRecvThread = new NetRecvThread();
    private BoxFragment mBoxFragment = null;
    private BranchFragment mBranchFragment = null;
    private LineFragment mLineFragment = null;
    private HomeFragment mHomeFragment = null;

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



    public void btmenuChanged(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);

        switch (id) {
            case 0:
                if(mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.content,mHomeFragment);
                }
                transaction.show(mHomeFragment);
                break;

            case 1:
                if(mLineFragment == null){
                    mLineFragment = new LineFragment();
                    transaction.add(R.id.content,mLineFragment);
                }
                transaction.show(mLineFragment);
                break;

            case 2:
                if(mBranchFragment == null){
                    mBranchFragment = new BranchFragment();
                    transaction.add(R.id.content,mBranchFragment);
                }
                transaction.show(mBranchFragment);
                break;

            case 3:
                if(mBoxFragment == null){
                    mBoxFragment = new BoxFragment();
                    transaction.add(R.id.content,mBoxFragment);
                }
                transaction.show(mBoxFragment);
                break;
        }
        transaction.commit();
    }


    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mBoxFragment != null) fragmentTransaction.hide(mBoxFragment);
        if (mBranchFragment != null) fragmentTransaction.hide(mBranchFragment);
        if (mLineFragment != null) fragmentTransaction.hide(mLineFragment);
        if (mHomeFragment != null) fragmentTransaction.hide(mHomeFragment);
    }

}
