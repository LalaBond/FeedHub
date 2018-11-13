package com.feedhub.someone.feedhub;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Context context;
    public RecyclerView recyclerView;
    public static LinearLayoutManager layoutManager;
    private Tracker mTracker;
    private AdView mAdView;
    private static int position = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context = this;


        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        MobileAds.initialize(this, "ca-app-pub-3533473961105694~3720970661");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        if (savedInstanceState != null ){
            position =  savedInstanceState.getInt("position");
            layoutManager.scrollToPosition(position);
        }

        //url of RSS feed
        //MAKE A CHECK FOR SETTINGS, WHATEVER USER SELECT ON SETTINGS NEEDS TO BE DISPLAYED
        String urlString = NewsFeedCompanies.Gizmodo;
        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                //what to do when the parsing is done
                //the Array List contains all article's data. For example you can use it for your adapter.

                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Task")
                        .setAction("Completed RSS Task")
                        .build());

                CustomAdapter adapter = new CustomAdapter(context, list);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError() {
                //what to do in case of error
                Log.e("Error", "Error parsing string");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("INFO", "Setting screen name: " + "MainActivity");
        mTracker.setScreenName("Image~" + "MainActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        position = layoutManager.findFirstVisibleItemPosition();

        outState.putInt("position", position);
    }


}
