package com.clever.www.busbar.line.linethd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.clever.www.busbar.R;
import com.clever.www.busbar.common.timer.HanderTimer;
import com.clever.www.busbar.dp.data.packages.base.DevDataBase;
import com.clever.www.busbar.dp.data.packages.devdata.DevObjData;
import com.clever.www.busbar.login.LoginStatus;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class LineThdActivity extends Activity {
    private ColumnChartData mColumnChartData; //柱状图数据
    private ColumnChartView mColumnChartView;
    private int mMode=0, mLine=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_thd_activity);
        mColumnChartView = (ColumnChartView) findViewById(R.id.chart);

        Intent intent = getIntent();
        int id = intent.getIntExtra("box_id", 0);

        initBtn();
        updateData();
        new Timers().start(500);
    }

    public static void actionStart(Context context, int boxId) {
        Intent intent = new Intent(context, LineThdActivity.class);
        intent.putExtra("box_id", boxId);
        context.startActivity(intent);
    }

    protected void initBtn()
    {
        ButtonListener listener =  new ButtonListener();
        findViewById(R.id.ua_btn).setOnClickListener(listener);
        findViewById(R.id.ub_btn).setOnClickListener(listener);
        findViewById(R.id.uc_btn).setOnClickListener(listener);

        findViewById(R.id.ia_btn).setOnClickListener(listener);
        findViewById(R.id.ib_btn).setOnClickListener(listener);
        findViewById(R.id.ic_btn).setOnClickListener(listener);
    }


    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ua_btn: mMode=0; mLine=0; break;
                case R.id.ub_btn: mMode=0; mLine=1; break;
                case R.id.uc_btn: mMode=0; mLine=2; break;
                case R.id.ia_btn: mMode=1; mLine=0; break;
                case R.id.ib_btn: mMode=1; mLine=1; break;
                case R.id.ic_btn: mMode=1; mLine=2; break;
            }
            updateData();
        }
    }


    protected void setTitle()
    {
        TextView tv = findViewById(R.id.text_view);
        String str = "L" + (mLine+1) + " ";
        if(mMode==1) {
            str += "电流";
        } else {
            str += "电压";
        }
        str += "谐波含量(THD)";

        tv.setText(str);
    }

    protected void updateData()
    {
        DevObjData obj = LoginStatus.getPacket(0).data.line;
        DevDataBase data = obj.cThdArray[mLine];
        if(mMode==0) data = obj.vThdArray[mLine];

        List<Integer> l = new ArrayList<>();
        for(int i=0; i<data.size(); ++i) {
            l.add(new Integer(data.get(i)));
        }

        updateChart(l);
        setTitle();
    }

    protected void updateChart(List<Integer> list){
        String[] xValues = new String[31];
        for(int i=0; i<xValues.length; ++i) xValues[i] = "" + (i+1);

        /*========== 柱状图数据填充 ==========*/
        List<Column> columnList = new ArrayList<>(); //柱子列表
        List<SubcolumnValue> subcolumnValueList;     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        List<AxisValue> axisValues = new ArrayList<>();//自定义横轴坐标值

        for (Integer i: list) {
            subcolumnValueList = new ArrayList<>();
            subcolumnValueList.add(new SubcolumnValue(i, ChartUtils.pickColor()));

            Column column = new Column(subcolumnValueList);
            columnList.add(column);
            column.setHasLabels(true);

            axisValues.add(new AxisValue(i).setLabel(xValues[i])); //设置坐标值
        }

        mColumnChartData = new ColumnChartData(columnList);               //设置数据

        /*===== 坐标轴相关设置 =====*/
        Axis axisX = new Axis(axisValues);
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Axis X");    //设置横轴名称
        axisY.setName("Axis Y");    //设置竖轴名称
        mColumnChartData.setAxisXBottom(axisX); //设置横轴
        mColumnChartData.setAxisYLeft(axisY);   //设置竖轴

        //以上所有设置的数据、坐标配置都已存放到mColumnChartData中，接下来给mColumnChartView设置这些配置
        mColumnChartView.setColumnChartData(mColumnChartData);
    }

    private class Timers extends HanderTimer {
        @Override
        public void timeout() {
            updateData();
        }
    }
}
