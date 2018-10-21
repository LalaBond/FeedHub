package com.feedhub.someone.feedhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.feedhub.someone.feedhub.model.ArticleSerializableModel;
import com.google.gson.Gson;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;


/**
 * Created by someone on 10/9/18.
 */
public class ArticleDetailActivity extends AppCompatActivity {

    private String title, author, link, date, description, content;
    private String articleString;
    private ArticleSerializableModel article;
    private TextView descriptionTV, authorTV, publicationDateTV;
    private ImageView imageView;
    private WebView webview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();
        article = (ArticleSerializableModel) intent.getSerializableExtra("article");

        SetupLayout();
        SetContent();

    }

    private void SetContent() {
//
//        Picasso.with(this).load(article.getImage()).into(imageView);
//        descriptionTV.setText(article.getDescription());
        webview.loadUrl(article.getLink());
    }

    private void SetupLayout() {

//        imageView = findViewById(R.id.imageView);
//        descriptionTV = findViewById(R.id.description);
        webview = findViewById(R.id.webview);

    }

}
