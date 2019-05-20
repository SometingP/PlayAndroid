package com.example.playandroid.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.corelib.entity.SearchDataEntity;
import com.example.playandroid.ArticleActivity;
import com.example.playandroid.R;

import java.util.List;


/**
 * @author 13973
 */
public class SearchDataApapter extends RecyclerView.Adapter<SearchDataApapter.ViewHolder> {
    private Context context;
    private List<SearchDataEntity.DatasBean> datas;

    public SearchDataApapter(Context context,List<SearchDataEntity.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.knowlege_list_item, parent, false);
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
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
}
