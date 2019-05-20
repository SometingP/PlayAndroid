package com.example.playandroid.fragment.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.corelib.entity.ArticleEntity;
import com.example.playandroid.ArticleActivity;
import com.example.playandroid.R;
import com.example.playandroid.collection.CollectionRequetModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页文章列表适配器
 *
 * @author 13973
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHoder> {
    private Context context;
    private List<ArticleEntity.DataBean.DatasBean> datasBeans;
    private CollectionRequetModel mModel;

    public ArticleListAdapter(Context context, List<ArticleEntity.DataBean.DatasBean> datasBeans) {
        this.context = context;
        this.datasBeans = datasBeans;
        mModel = new CollectionRequetModel();
    }

    @Override
    public ArticleListAdapter.ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(ArticleListAdapter.ViewHoder holder, final int position) {

        holder.mAuthor.setText(datasBeans.get(position).getAuthor());
        holder.mChapterName.setText(datasBeans.get(position).getSuperChapterName()
                + "/" + datasBeans.get(position).getChapterName());
        holder.mTitle.setText(datasBeans.get(position).getTitle());
            holder.mCollection.setChecked(datasBeans.get(position).isCollect());
        holder.cardView.setEnabled(true);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticleActivity.class);
                intent.putExtra("title", datasBeans.get(position).getTitle());
                intent.putExtra("url", datasBeans.get(position).getLink());
                context.startActivity(intent);
            }
        });

        holder.mCollection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mModel.setCollectionArticle(datasBeans.get(position).getId());
                } else {
                    mModel.setCollectionArticle(datasBeans.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datasBeans.size();
    }


    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView mAuthor, mChapterName, mTitle;
        CardView cardView;
        CheckBox mCollection;

        public ViewHoder(View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author);
            mChapterName = itemView.findViewById(R.id.chapter_name);
            mTitle = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.card_view);
            mCollection = itemView.findViewById(R.id.checkbox_collection);
        }
    }


    public void setArticleListDatas(List<ArticleEntity.DataBean.DatasBean> datasBeans) {
        if (this.datasBeans == null) {
            this.datasBeans = new ArrayList<>();
            this.datasBeans.addAll(datasBeans);
        } else {
            this.datasBeans.addAll(datasBeans);
        }
        this.notifyDataSetChanged();
    }
}
