package com.deshine.huishu.app.meitu.view;

import com.deshine.huishu.app.meitu.model.bean.PhotoGirl;

import java.util.List;

/**
 * Created by lvr on 2017/2/20.
 */

public interface PhotoView {
    void returnPhotoList(List<PhotoGirl> photoGirlList);
    void showErrorTip();
}
