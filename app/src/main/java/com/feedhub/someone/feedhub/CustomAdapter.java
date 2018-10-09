package com.feedhub.someone.feedhub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by someone on 10/4/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Article> list;


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

        String image = list.get(position).getImage();
        Picasso.with(context).load(image).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        int x = list.size();
        return x;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public String title, author, link, date, description, content, image;

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.articleImageView);

        }
    }
}
