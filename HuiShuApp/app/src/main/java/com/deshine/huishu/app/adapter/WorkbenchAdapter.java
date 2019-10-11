package com.deshine.huishu.app.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.bean.ChannelBean;
import com.deshine.huishu.app.widget.ItemDragHelperCallback;
import com.deshine.huishu.app.workbench.model.bean.Workbench;

import java.util.Collections;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by lvr on 2017/2/14.
 */

public class WorkbenchAdapter extends RecyclerView.Adapter<WorkbenchAdapter.WorkbenchViewHolder> /*implements ItemDragHelperCallback.OnItemMoveListener*/ {
    private final Context context;
    private final LayoutInflater inflater;
    private List<Workbench> mTables;
    private ItemDragHelperCallback mItemDragHelperCallback;
    private OnItemClickListener mOnItemClickListener;

    public WorkbenchAdapter(Context context, List<Workbench> tables) {
        this.context = context;
        this.mTables = tables;
        this.inflater = LayoutInflater.from(context);
    }

    public void setItemDragHelperCallback(ItemDragHelperCallback itemDragHelperCallback) {
        mItemDragHelperCallback = itemDragHelperCallback;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public WorkbenchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WorkbenchViewHolder(inflater.inflate(R.layout.workbench_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(WorkbenchViewHolder holder, int position) {
        Workbench table = mTables.get(position);
        holder.mTextView.setText(table.getName());
        holder.mImageView.setImageResource(table.getImgRes());
        //handleLongPress(holder, table);
        handleOnClick(holder, table);
    }

    //处理点击事件
    private void handleOnClick(final WorkbenchViewHolder holder, final Workbench table) {
        if (mOnItemClickListener != null) {
            holder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //对项目点击后增删操作的监听
                    mOnItemClickListener.onItemClick(view, holder.getLayoutPosition());
//                    if (!table.getNewsChannelFixed()) {
//                        //对项目点击后增删操作的监听
//                        mOnItemClickListener.onItemClick(view, holder.getLayoutPosition());
//                    }
                }
            });
        }
    }

    //处理长按事件 开启ItemDragHelperCallBack拖拽
    private void handleLongPress(WorkbenchViewHolder holder, final Workbench table) {
        if (mItemDragHelperCallback != null) {
            holder.mLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mItemDragHelperCallback.setLongPressEnabled(table.getIndex() == 0 ? false : true);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTables.size();
    }

//    @Override
//    public boolean onItemMove(int fromPosition, int toPosition) {
//        if (isChannelFixed(fromPosition, toPosition)) {
//            return false;
//        }
//        //在我的频道子频道的移动
//        Collections.swap(getAdapterData(), fromPosition, toPosition);
//        notifyItemMoved(fromPosition, toPosition);
//        //通知顺序变换，存储，设置频道顺序，以及显示的顺序
//        System.out.println("发送移动的消息");
//        EventBus.getDefault().post(new ChannelBean(getAdapterData()));
//        return true;
//    }

    //不能移动头条
    private boolean isChannelFixed(int fromPosition, int toPosition) {
        return fromPosition == 0 || toPosition == 0;
    }

    public class WorkbenchViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private ImageView mImageView;
        private CardView mCardView;
        private RelativeLayout mLayout;

        public WorkbenchViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_channel_logo);
            mTextView = (TextView) itemView.findViewById(R.id.tv_channel_name);
            mCardView = (CardView) itemView.findViewById(R.id.cardView);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.rl_root);
        }
    }

    public List<Workbench> getAdapterData() {
        return mTables;
    }

    //Item点击事件的监听接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
