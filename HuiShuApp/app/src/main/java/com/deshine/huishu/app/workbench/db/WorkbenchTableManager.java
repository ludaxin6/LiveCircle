/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.deshine.huishu.app.workbench.db;


import com.deshine.huishu.app.R;
import com.deshine.huishu.app.api.ApiConstants;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;
import com.deshine.huishu.app.workbench.model.bean.Workbench;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkbenchTableManager {

    /**
     * 加载工作台
     *
     * @return
     */


    public static List<Workbench> loadAll() {
        List<String> channelName = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.workbench_channel_name));
        List<String> channelId = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.workbench_channel_id));
        List<Integer> channelImgRes = new ArrayList<Integer>();
        channelImgRes.add(R.drawable.news_house);
        channelImgRes.add(R.drawable.news_play);
        channelImgRes.add(R.drawable.news_movie);
        channelImgRes.add(R.drawable.news_car);
        channelImgRes.add(R.drawable.news_game);
        channelImgRes.add(R.drawable.news_education);
        channelImgRes.add(R.drawable.news_travel);
        channelImgRes.add(R.drawable.news_mobile);
        channelImgRes.add(R.drawable.news_social);
        channelImgRes.add(R.drawable.news_social);
        ArrayList<Workbench> workbenchList = new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            Workbench entity = new Workbench( channelId.get(i), channelName.get(i) ,i, channelImgRes.get(i));
            workbenchList.add(entity);
        }
        return workbenchList;
    }
}
