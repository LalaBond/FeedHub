package com.feedhub.someone.feedhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

        Intent intent = getIntent();
        article = (ArticleSerializableModel) intent.getSerializableExtra("article");

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                sharingIntent.putExtra(Intent.EXTRA_HTML_TEXT, "www.blah.com");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, article.getLink());
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
