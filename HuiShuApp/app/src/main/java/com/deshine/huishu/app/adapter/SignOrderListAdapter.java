package com.deshine.huishu.app.adapter;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;
import com.deshine.huishu.app.utils.DateUtil;
import com.deshine.huishu.app.widget.LoadMoreFooterView;

import java.util.Date;
import java.util.List;

/**
 * Created by lvr on 2017/2/8.
 */

public class SignOrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflater;
    private List<FreightOrderDto> freightOrderDtoList ;
    private int total;
    private Context mContext;
    private SignOrderListAdapter.OnItemClickListener mOnItemClickListener;
    private static final int TYPE_SINGLE = 0;//正常内容
    private final static int TYPE_FOOTER=1;//下拉刷新
    public SignOrderListAdapter(Context context, List<FreightOrderDto> list, int total) {
        this.mContext = context;
        this.freightOrderDtoList = list;
        this.total = total;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {

        if (position==freightOrderDtoList.size())
        {
            return TYPE_FOOTER;
        }
        return TYPE_SINGLE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SINGLE:
                return new SingleViewHolder(inflater.inflate(R.layout.item_sign_order, parent, false));
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.layout_irecyclerview_load_more_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(getItemViewType(position) == TYPE_FOOTER){
           setFooterItemValues((FooterViewHolder) holder, position);
       }else{
           setNormalItemValues((SingleViewHolder) holder,position);
       }
    }

    @Override
    public int getItemCount() {
        return freightOrderDtoList.size() +1 ;
    }

    //新闻模式下item值的设置
    private void setNormalItemValues(final SingleViewHolder holder, int position) {
        final FreightOrderDto freightOrderDto = freightOrderDtoList.get(position);
        String addressAll = freightOrderDto.getConsigneeAddress();
        String[] infos = addressAll.split(" ");
        String title = freightOrderDto.getConsigneeName();
        final String content = infos[0];
        Date bookDate = freightOrderDto.getBookedDate();
        Date now = new Date();
        String yesterdayStr = DateUtil.getYesterday().replace(" 00:00:00","");
        String bookDateStr = DateUtil.format(bookDate, "yyyy-MM-dd");
        String nowStr = DateUtil.format(now, "yyyy-MM-dd");
        String timeStr = "";
        boolean isChaoQi = false;
        if(nowStr.equals(bookDateStr)){
            timeStr = "今天";
        }else if(yesterdayStr.equals(bookDateStr)){
            timeStr = "昨天";
            isChaoQi = true;
        }else {
            timeStr = DateUtil.format(bookDate, "MM-dd");
            isChaoQi = true;
        }
        if(isChaoQi){
            holder.mImageView.setText("超时未上传");
            holder.mImageView.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        }else{
            holder.mImageView.setText("未上传");
            holder.mImageView.setBackgroundColor(mContext.getResources().getColor(R.color.hs_warning));
        }
        holder.mTitle.setText(title);
        holder.mContent.setText(content);
        holder.mTime.setText(timeStr);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //单条点击事件
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, holder.getLayoutPosition());
                }
            }
        });
    }

    private void setFooterItemValues(final FooterViewHolder holder, int position) {
        if(freightOrderDtoList.size()<total){
            holder.mFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        }else{
            holder.mFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }

    }


    public class SingleViewHolder extends RecyclerView.ViewHolder{
        private TextView mImageView;
        private TextView mTitle;
        private TextView mContent;
        private TextView mTime;
        private RelativeLayout mLayout;
        public SingleViewHolder(View itemView) {
            super(itemView);
            mImageView = (TextView) itemView.findViewById(R.id.news_summary_photo_iv);
            mTitle = (TextView) itemView.findViewById(R.id.news_summary_title_tv);
            mContent = (TextView) itemView.findViewById(R.id.news_summary_digest_tv);
            mTime = (TextView) itemView.findViewById(R.id.news_summary_ptime_tv);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.rl_root);

        }

    }
    private class FooterViewHolder extends RecyclerView.ViewHolder{
        private LoadMoreFooterView mFooterView;
        public FooterViewHolder(View itemView) {
            super(itemView);
            mFooterView = (LoadMoreFooterView)itemView;
        }
    }

    public List<FreightOrderDto> getAdapterData(){
        return freightOrderDtoList;
    }

    public void setOnItemClickListener(SignOrderListAdapter.OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    //Item点击事件的监听接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
