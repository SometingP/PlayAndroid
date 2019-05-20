package com.example.playandroid.fragment.official;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.corelib.entity.OffciaAccountEntity;
import com.example.playandroid.ArticleActivity;
import com.example.playandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人公众号数据适配器
 * @author 彭翔宇
 */
public class OffciaAccountItemAdapter extends RecyclerView.Adapter<OffciaAccountItemAdapter.ViewHolder> {
    private boolean isData = false;
    private List<OffciaAccountEntity.DataBean.DatasBean> datas;
    private Context context;

    public OffciaAccountItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mAuthor.setText(datas.get(position).getAuthor());
        holder.mTitle.setText(datas.get(position).getTitle());
        holder.mChapterName.setText(datas.get(position).getSuperChapterName()+"/"+datas.get(position).getChapterName());
        holder.mCardView.setEnabled(true);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ArticleActivity.class);
                intent.putExtra("title",datas.get(position).getTitle());
                intent.putExtra("url",datas.get(position).getLink());
                context.startActivity(intent);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mChapterName;
        private TextView mTitle;
        private TextView mAuthor;
        private CardView mCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author);
            mTitle = itemView.findViewById(R.id.title);
            mChapterName = itemView.findViewById(R.id.chapter_name);
            mCardView = itemView.findViewById(R.id.card_view);
        }
    }

    public void setOffciaRecyclerDatas(List<OffciaAccountEntity.DataBean.DatasBean> datas) {
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
