package com.deshine.huishu.app.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.cameralib.util.LogUtil;


public class LoadingDialog {
    /** 加载数据对话框 */
    private static Dialog mLoadingDialog;

    /**
     * 显示加载对话框
     * @param context 上下文
     * @param msg 对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public static Dialog showDialogForLoading(Activity context, String msg, boolean cancelable) {
        if(!context.isFinishing()){
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
            loadingText.setText(msg);

            mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mLoadingDialog.show();
            return  mLoadingDialog;
        }else {
            return null;
        }
    }

    public static Dialog showDialogForLoading(Activity context) {
        if(!context.isFinishing()) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            TextView loadingText = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
            loadingText.setText("加载中...");

            mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mLoadingDialog.show();
            return mLoadingDialog;
        }else{
            return null;
        }
    }

    /**
     * 关闭加载对话框
     */
    public static void cancelDialogForLoading() {
        if(mLoadingDialog != null) {
            mLoadingDialog.cancel();
        }
    }
}
