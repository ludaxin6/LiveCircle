/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.deshine.huishu.app.orcameralib.util;

import android.content.res.Resources;

public class DimensionUtil {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
