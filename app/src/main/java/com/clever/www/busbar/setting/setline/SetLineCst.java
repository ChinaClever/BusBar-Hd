package com.clever.www.busbar.setting.setline;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.clever.www.busbar.R;

/**
 * Created by Lzy on 17-8-14.
 */

public class SetLineCst extends LinearLayout{
    public SetLineCst(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.set_line_cst, this);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress(35);

        progressBar.setSecondaryProgress(80);

    }
}
