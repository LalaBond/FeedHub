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

import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by someone on 10/4/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Article> list;
    private String image;


    public CustomAdapter(Context context, ArrayList<Article> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.article_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {

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
                    Intent moviePreview = new Intent(context, ArticleDetailActivity.class);

                    moviePreview.putExtra("article", (Parcelable) list.get(getAdapterPosition()));
                    context.startActivity(moviePreview);

                }
            });

        }
    }
}
