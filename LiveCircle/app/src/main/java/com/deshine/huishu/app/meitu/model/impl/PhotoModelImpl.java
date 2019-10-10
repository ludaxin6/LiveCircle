package com.deshine.huishu.app.meitu.model.impl;

import com.deshine.huishu.app.api.ApiService;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.client.RetrofitClient;
import com.deshine.huishu.app.meitu.model.PhotoModel;
import com.deshine.huishu.app.meitu.model.bean.GirlData;
import com.deshine.huishu.app.meitu.model.bean.PhotoGirl;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by lvr on 2017/2/20.
 */

public class PhotoModelImpl implements PhotoModel {
    @Override
    public Observable<List<PhotoGirl>> getPhotoList(int size, int page) {
        RetrofitClient client = RetrofitClient.getInstance(AppApplication.getAppContext(), ApiService.PHOTO_BASE_URL);
        ApiService api = client.create(ApiService.class);

        return api.getPhotoList(size,page).map(new Function<GirlData, List<PhotoGirl>>() {
            @Override
            public List<PhotoGirl> apply(GirlData data) throws Exception {
                return  data.getResults();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
