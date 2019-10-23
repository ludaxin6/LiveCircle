package com.deshine.huishu.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.meitu.model.bean.PhotoGirl;
import com.deshine.huishu.app.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by lvr on 2017/2/20.
 */

public class CommonAffixAdapter extends RecyclerView.Adapter<CommonAffixAdapter.PhotoViewHolder> {
    private  Context mContext;
    private List<CommonAffix> mData;
    private final LayoutInflater inflater;
    private CommonAffixAdapter.OnItemClickListener mOnItemClickListener;
    public CommonAffixAdapter(Context context, List<CommonAffix> list) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mData = list;
    }


    @Override
    public CommonAffixAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(inflater.inflate(R.layout.item_photo_girl,parent,false));
    }

    @Override
    public void onBindViewHolder(CommonAffixAdapter.PhotoViewHolder holder, int position) {
        ImageLoaderUtils.display(mContext,holder.mImageView,mData.get(position).getAffixUrl());
        handleOnClick(holder, mData.get(position));
    }

    //处理点击事件
    private void handleOnClick(final CommonAffixAdapter.PhotoViewHolder holder, final CommonAffix commonAffix) {
        if (mOnItemClickListener != null) {
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //对项目点击后增删操作的监听
                    mOnItemClickListener.onItemClick(view, holder.getLayoutPosition());
                }
            });
        }
    }
    public void setOnItemClickListener(CommonAffixAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
        }

    }
    public List<CommonAffix> getAdapterData(){
        return mData;
    }

    //Item点击事件的监听接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
