package com.example.playandroid.fragment.knowledge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.corelib.entity.KnowlegeEntity;
import com.example.playandroid.fragment.knowledge.KnowlegeItemActivity;
import com.example.playandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识体系列表适配器
 * @author 彭翔宇
 */
public class KnowlegeAdapter extends RecyclerView.Adapter<KnowlegeAdapter.ViewHoder> {
    private Context context;
    private List<KnowlegeEntity.DataBean> datas;
    private boolean isData;

    public KnowlegeAdapter(Context context) {
        this.context = context;
        isData = false;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.knowlege_list_item, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, final int position) {
        holder.mKnowlegeName.setText(datas.get(position).getName());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < datas.get(position).getChildren().size(); i++) {
            stringBuffer.append(datas.get(position).getChildren().get(i).getName());
            stringBuffer.append("  ");
        }
        holder.mKnowlegeChildrenNames.setText(stringBuffer.toString());
        holder.mCardView.setEnabled(true);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,KnowlegeItemActivity.class);
                intent.putExtra("data",datas.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (isData) {
            return datas.size();
        } else {
            return 0;
        }
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        public TextView mKnowlegeName;
        public TextView mKnowlegeChildrenNames;
        public CardView mCardView;

        public ViewHoder(View itemView) {
            super(itemView);
            mKnowlegeName = itemView.findViewById(R.id.know_name);
            mKnowlegeChildrenNames = itemView.findViewById(R.id.know_children_names);
            mCardView = itemView.findViewById(R.id.card_view);
        }
    }

    /**
     * 更新数据
     *
     * @param datas
     */
    public void setKnowlegeDataList(List<KnowlegeEntity.DataBean> datas) {
        isData = true;
        if (this.datas == null) {
            this.datas = new ArrayList<>();
            this.datas.addAll(datas);
        } else {
            this.datas.clear();
            this.datas.addAll(datas);
        }
        this.notifyDataSetChanged();
    }
}
