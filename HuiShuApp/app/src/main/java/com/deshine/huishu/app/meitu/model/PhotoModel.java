package com.deshine.huishu.app.meitu.model;

import com.deshine.huishu.app.meitu.model.bean.PhotoGirl;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lvr on 2017/2/20.
 */

public interface PhotoModel {
    Observable<List<PhotoGirl>> getPhotoList(int size, int page);
}
