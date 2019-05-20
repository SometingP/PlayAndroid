package com.example.playandroid.fragment.project;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.playandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目列表数据适配器
 * @author pengxiangyu
 * @date 2019/4/9
 */

public class ProjectViewAdapter extends RecyclerView.Adapter<ProjectViewAdapter.ViewHodler> {
    private boolean isData;
    private Context context;
    private List<ProjectEntity.DataBean.DatasBean> datas;

    public ProjectViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_view_item
                , parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.mTitle.setText(datas.get(position).getTitle());
        holder.mContent.setText(datas.get(position).getDesc());
        holder.mDate.setText(datas.get(position).getNiceDate());
        holder.mAutor.setText(datas.get(position).getAuthor());
        Glide.with(context.getApplicationContext()).load(datas.get(position).getEnvelopePic());
        holder.mCard.setClickable(true);
        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (isData) {
            return datas.size();
        }
        return 0;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        ImageView mIcon;
        TextView mTitle;
        TextView mContent;
        TextView mDate;
        TextView mAutor;
        CardView mCard;

        public ViewHodler(View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.icon);
            mTitle = itemView.findViewById(R.id.title);
            mContent = itemView.findViewById(R.id.content);
            mDate = itemView.findViewById(R.id.date);
            mAutor = itemView.findViewById(R.id.author);
            mCard = itemView.findViewById(R.id.cardviwe);
        }
    }

    public void setProjectDatas(List<ProjectEntity.DataBean.DatasBean> data) {
        isData = true;
        if (datas == null) {
            datas = new ArrayList<>();
            datas.addAll(data);
            notifyDataSetChanged();
        } else {
            datas.addAll(data);
            notifyDataSetChanged();
        }
    }
}
