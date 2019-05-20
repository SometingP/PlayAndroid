package com.example.playandroid.fragment.knowledge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.corelib.entity.ArticleEntity;
import com.example.playandroid.ArticleActivity;
import com.example.playandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识体系一级菜单适配器
 * @author 彭翔宇
 */
public class KnowItemRecyclerAdapter extends RecyclerView.Adapter<KnowItemRecyclerAdapter.ViewHodler> {
    private boolean isData;
    private List<ArticleEntity.DataBean.DatasBean> datas;
    private Context context;

    public KnowItemRecyclerAdapter(Context context) {
        isData = false;
        this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, final int position) {
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

    class ViewHodler extends RecyclerView.ViewHolder {
        private TextView mChapterName;
        private TextView mTitle;
        private TextView mAuthor;
        private CardView mCardView;

        public ViewHodler(View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author);
            mTitle = itemView.findViewById(R.id.title);
            mChapterName = itemView.findViewById(R.id.chapter_name);
            mCardView = itemView.findViewById(R.id.card_view);
        }
    }

    public void setKnowItemRecyclerDatas(List<ArticleEntity.DataBean.DatasBean> datas) {
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
