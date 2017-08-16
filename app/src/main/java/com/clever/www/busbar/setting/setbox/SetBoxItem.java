package com.clever.www.busbar.setting.setbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lzy on 17-8-16.
 */

public class SetBoxItem {
    public int id = 0;
    public String name;
    public int num = 3;
    public List<Integer> array = new ArrayList<>();

    public SetBoxItem(int id) {
        this.id = id ;
    }
}
