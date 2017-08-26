package com.clever.www.busbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.clever.www.busbar.boxlist.BoxListFragment;
import com.clever.www.busbar.branch.BranchFragment;
import com.clever.www.busbar.dp.dev.DevSpiedThread;
import com.clever.www.busbar.home.HomeFragment;
import com.clever.www.busbar.line.LineFragment;
import com.clever.www.busbar.login.LoginStatus;
import com.clever.www.busbar.navigation.NavigationFragment;
import com.clever.www.busbar.net.NetRecvThread;
import com.clever.www.busbar.setting.SetCheckPwdActivity;

public class MainActivity extends AppCompatActivity {
    private NetRecvThread mNetRecvThread = new NetRecvThread();
    private NavigationFragment mNavigationFragment = null;
    private BoxListFragment mBoxListFragment = null;
    private BranchFragment mBranchFragment = null;
    private LineFragment mLineFragment = null;
    private HomeFragment mHomeFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initFun();
        mNavigationFragment = (NavigationFragment) getSupportFragmentManager()
                .findFragmentById( R.id.navigation_lzy);
    }

    private void initFun() {
        mNetRecvThread.initNet();
        DevSpiedThread.get().startThread();
    }


    private boolean startSetMenuActivity() {
        boolean ret = true;
        if(LoginStatus.isLogin) {
            Intent intent = new Intent(MainActivity.this, SetCheckPwdActivity.class);
            startActivityForResult(intent, 1);
        } else {
            Toast.makeText(this, R.string.set_no_login, Toast.LENGTH_SHORT).show();
            ret = false;
        }

        return  ret;
    }


    public void btmenuChanged(int id) {

        if(id == 4) {
            startSetMenuActivity();
        }

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
                if(mBoxListFragment == null){
                    mBoxListFragment = new BoxListFragment();
                    transaction.add(R.id.content, mBoxListFragment);
                }
                transaction.show(mBoxListFragment);
                break;



            default:
                mNavigationFragment.setHomeBtn();
                return;
        }
        transaction.commit();
    }


    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mBoxListFragment != null) fragmentTransaction.hide(mBoxListFragment);
        if (mBranchFragment != null) fragmentTransaction.hide(mBranchFragment);
        if (mLineFragment != null) fragmentTransaction.hide(mLineFragment);
        if (mHomeFragment != null) fragmentTransaction.hide(mHomeFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    int id = data.getIntExtra("set_menu", 0);
                    if(id > 0) {
                        btmenuChanged(4 + id);
                    } else {
                        btmenuChanged(10);
                    }
                }
                break;
        }
    }

}
