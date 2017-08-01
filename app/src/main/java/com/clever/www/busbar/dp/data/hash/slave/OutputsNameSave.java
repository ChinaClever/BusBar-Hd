package com.clever.www.busbar.dp.data.hash.slave;


import com.clever.www.busbar.dp.data.packages.output.DevOutputsName;
import com.clever.www.busbar.net.data.packages.NetDataDomain;

/**
 * Author: lzy. Created on: 16-11-8.
 */

public class OutputsNameSave {
    private HashSlaveCom mCommon = new HashSlaveCom();

    /**
     * @brief 设置输出位的名称
     * @param name
     * @param data
     */
    public void outputName(DevOutputsName name, NetDataDomain data) {
        int output = data.fn[1];
        if((output >= 0) && (output < 64))  {  // 输出位最大32位
            String str = mCommon.charToString(data.data, data.len);
            name.set(output, str); // 设置输出位的名称
        }
    }
}
