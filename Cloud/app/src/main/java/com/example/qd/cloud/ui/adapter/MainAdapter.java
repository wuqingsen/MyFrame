package com.example.qd.cloud.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qd.cloud.R;
import com.example.qd.cloud.bean.UserInfoBean;
import com.example.qd.cloud.ui.base.BaseCloudAdapter;
import com.example.qd.cloud.ui.base.BaseRecyclerViewHolder;
import com.example.qd.cloud.utils.RealmHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name:Wu.
 * Date:2018/11/4.
 * Describe：demo adapter
 */

public class MainAdapter extends BaseCloudAdapter<UserInfoBean> {
    private RealmHelper mRealmHelper;

    public MainAdapter(Context context, List<UserInfoBean> datas) {
        super(context, datas);
        mRealmHelper = new RealmHelper(mContext);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder baseHolder, int position) {
        if (baseHolder instanceof MyViewHolder) {
//            final MyViewHolder holder = (MyViewHolder) baseHolder;
//            final UserInfoBean model = mDatas.get(position);
//            holder.tv_id.setText(model.getUid());
//            holder.tv_name.setText("姓名：" + model.getName() + "  年龄：" + model.getAge());
//            if (mRealmHelper.isUserInfoExist(model.getUid())) {
//                holder.iv_like.setSelected(true);
//            } else {
//                holder.iv_like.setSelected(false);
//            }
//
//            holder.iv_like.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (holder.iv_like.isSelected()) {
//                        holder.iv_like.setSelected(false);
//                        mRealmHelper.deleteUserInfo(model.getUid());
//                    } else {
//                        holder.iv_like.setSelected(true);
//                        mRealmHelper.addUserInfo(model);
//                    }
//                }
//            });
        }
    }

    @Override
    protected BaseRecyclerViewHolder createView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    class MyViewHolder extends BaseRecyclerViewHolder {
//        @BindView(R.id.tv_id)
//        TextView tv_id;
//        @BindView(R.id.tv_name)
//        TextView tv_name;
//        @BindView(R.id.iv_like)
//        ImageView iv_like;

        public MyViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
        }

    }
}