package com.clever.www.busbar.line.total;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clever.www.busbar.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Lzy on 17-8-10.
 */

public class PieChartCst extends LinearLayout{
    private TextView unitTv;
    private PieChartView chart;
    private PieChartData data;

    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = true;
    private boolean hasCenterText1 = true;
    private boolean hasCenterText2 = false;
    private boolean isExploded = true;
    List<SliceValue> values = new ArrayList<>();

    public PieChartCst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.pie_chart_cst, this);
        chart = view.findViewById(R.id.piechart);
        unitTv = view.findViewById(R.id.unit_tv);

        generateData();
        initViews();
    }

    private void generateData() {
        SliceValue sliceValue = new SliceValue(90.0f,Color.WHITE);
        values.add(sliceValue);

        sliceValue = new SliceValue(0f,Color.GREEN);
        values.add(sliceValue);

        sliceValue = new SliceValue(270.0f,Color.GRAY);
        values.add(sliceValue);
    }

    private void initViews() {
        chart.setCircleFillRatio(0.9f);//设置图所占整个view的比例  当有外面的数据时使用，防止数据显示不全
        data = new PieChartData(values);
        data.setHasLabels(hasLabels);//显示数据
        data.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        data.setHasLabelsOutside(hasLabelsOutside);//占的百分比是否显示在饼图外面
        data.setHasCenterCircle(hasCenterCircle);;//是否是环形显示
        data.setCenterCircleScale(0.7f);////设置环形的大小级别
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);////设置值得背景透明
        data.setValueLabelBackgroundEnabled(false);//数据背景不显示
        data.setValueLabelsTextColor(Color.BLACK);

        if (isExploded) {
            data.setSlicesSpacing(0);//设置间隔为0
        }

        if (hasCenterText1) {
            data.setCenterText1("---");
            data.setCenterText1Color(Color.BLACK);////设置值得颜色*/
        }

        if (hasCenterText2) {
            data.setCenterText2("---");
            data.setCenterText2FontSize(32);
        }

        chart.setPieChartData(data);
    }

    public void setValue(int value, int max, int rate, String unitStr) {
        String str = value + "";

        if(rate > 1) {
            str = (value / (rate*1.0)) + "";
        }
        data.setCenterText1(str);

        if (hasCenterText2) {
            data.setCenterText2(unitStr);
        } else {
            unitTv.setText(unitStr);
        }

        if(max > 0) {
            int maxValue = max + max / 4;
            if (maxValue < 1) maxValue = 100;

            values.get(0).setValue(maxValue / 4.0f);
            values.get(1).setValue(value / 1.0f);
            values.get(2).setValue((max - value) / 1.0f);
        }

        chart.startDataAnimation();
    }

}
