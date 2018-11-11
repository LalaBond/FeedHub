package com.feedhub.someone.feedhub;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feedhub.someone.feedhub.model.ArticleSerializableModel;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;
import com.squareup.picasso.Picasso;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by someone on 10/4/18.
 */

public class WidgetCustomAdapter extends RecyclerView.Adapter<WidgetCustomAdapter.ViewHolder> implements Serializable{

    private Context context;
    private List<ArticleSerializableModel> list;
    private String image;


    public WidgetCustomAdapter(Context context, List<ArticleSerializableModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public WidgetCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.article_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WidgetCustomAdapter.ViewHolder holder, int position) {

        image = list.get(position).getImage();
        Picasso.with(context).load(image).into(holder.imageView);
        holder.imageView.setTag(position);

        holder.titleTV.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        int x = list.size();
        return x;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTV;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.articleImageView);
            titleTV = itemView.findViewById(R.id.titleTV);

            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent articlePreview = new Intent(context, ArticleDetailActivity.class);

                    //ArticleSerializableModel article = Helper.ArticleToArticleSerializableModelConverter(list.get(getAdapterPosition()));
                    articlePreview.putExtra("article", (Serializable) list.get(getAdapterPosition()));
                    context.startActivity(articlePreview);

                }
            });

        }
    }
}
