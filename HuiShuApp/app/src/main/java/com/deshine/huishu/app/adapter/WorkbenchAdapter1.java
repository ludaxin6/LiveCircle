package com.deshine.huishu.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.workbench.model.bean.Workbench;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvr on 2017/2/14.
 */

public class WorkbenchAdapter1 extends ArrayAdapter<Workbench> /*implements ItemDragHelperCallback.OnItemMoveListener*/ {
    private final Context context;
    private final LayoutInflater inflater;
    private List<Workbench> mTables;
    private OnItemClickListener mOnItemClickListener;
    private int resource;
    public WorkbenchAdapter1(@NonNull Context context, int resource, @NonNull List<Workbench> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        mTables = objects;
    }
    public void setGridData(List<Workbench> mGridData) {
        this.mTables = mGridData;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkbenchViewHolder holder;
        Workbench table = mTables.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
            holder = new WorkbenchViewHolder(convertView);
        } else {
            holder = (WorkbenchViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(table.getName());
        holder.mIconTextView.setText(table.getImgRes());
        holder.mIconTextView.setTextColor(context.getResources().getColor(table.getColorRes()));
//        if((position+1)%3==0) {
//            holder.lineRight.setVisibility(View.GONE);
//        }
        handleOnClick(holder, position);
        convertView.setTag(holder);
        return convertView;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    //处理点击事件
    private void handleOnClick(final WorkbenchViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            holder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //对项目点击后增删操作的监听
                    mOnItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getCount() {
        return mTables.size();
    }

    public class WorkbenchViewHolder {
        private TextView mTextView;
        private TextView mIconTextView;
        private RelativeLayout mLayout;
        private LinearLayout mLayoutMain;
        private View lineRight;
        private View lineBottom;

        public WorkbenchViewHolder(View itemView) {
            mIconTextView = (TextView) itemView.findViewById(R.id.workbench_logo);
            mTextView = (TextView) itemView.findViewById(R.id.workbench_label);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.workbench_item);
            mLayoutMain = (LinearLayout) itemView.findViewById(R.id.workbench_item_main);
            lineBottom = itemView.findViewById(R.id.workbench_item_bottomline);
            lineRight = itemView.findViewById(R.id.workbench_item_rightline);
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
