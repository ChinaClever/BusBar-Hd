package com.clever.www.busbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void changedFragment(int id) {
        Toast.makeText(this, "lzy: " + id, Toast.LENGTH_LONG).show();
    }

}
