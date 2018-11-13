package com.feedhub.someone.feedhub.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.feedhub.someone.feedhub.MainActivity;
import com.feedhub.someone.feedhub.R;
import com.feedhub.someone.feedhub.data.FavoriteNewsContract;
import com.feedhub.someone.feedhub.model.ArticleSerializableModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * Created by someone on 11/10/18.
 * Handles the task of filling up the widget with data
 */

public class FeedhubWidgetViewService extends RemoteViewsService {

    private int pos;
    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        if(intent != null) {

            pos = intent.getIntExtra("pos", 0);

        }
        return new GridRemoteViewsFactory(this.getApplicationContext(), pos);
    }
}


class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<ArticleSerializableModel> body;
    private Context context;
    private int position;


    public GridRemoteViewsFactory(Context applicationContext, int position) {

        context = applicationContext;
        this.position = position;

        GetFavoriteArticles();

    }

    private List<ArticleSerializableModel> parseToArticleObject(Cursor queryResults) {


        List<ArticleSerializableModel> widgetArticle;
         //parse my databse data (cursor) to an article object in order to display
        int articleIndex = queryResults.getColumnIndex(FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_ID);
        int articleTitle = queryResults.getColumnIndex(FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_TITLE);
        int articleDate = queryResults.getColumnIndex(FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_DATE);
        int articleDescription = queryResults.getColumnIndex(FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_DESCRIPTION);
        int articleLink = queryResults.getColumnIndex(FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_LINK);
        int articleImage = queryResults.getColumnIndex(FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_IMAGE);

        int results = queryResults.getCount();

        //MoviesResponse movieResponse = new MoviesResponse();
        ArticleSerializableModel [] articleModel = new ArticleSerializableModel[results];


        /*parsing elements of the database to a Movie Response model */
        for (int i = 0; i < results; i++) {
            queryResults.moveToPosition(i);

            /*get from db and set values of data to model*/
            articleModel[i] = new ArticleSerializableModel();
            //articleModel[i].setTitle(queryResults.getInt(movieIndex));
            articleModel[i].setTitle(queryResults.getString(articleTitle));
//            articleModel[i].setPubDate(queryResults.getString(articleDate));
//            articleModel[i].setVote_average(Double.parseDouble(queryResults.getString(movieRating)));
//            articleModel[i].setOverview(queryResults.getString(movieDescription));
            articleModel[i].setLink(queryResults.getString(articleLink));
            articleModel[i].setImage(queryResults.getString(articleImage));


        }
        widgetArticle = Arrays.asList(articleModel);

        return widgetArticle;
    }

    @Override
    public void onCreate() {

        Log.e("TAG", "ENTERING ONCREATE WIDGET");

    }

    private List<ArticleSerializableModel> GetFavoriteArticles() {

        try {

            /*fetching data from db with AsyncTask*/

            new LoadWidgetDataTask().execute().get();
            //Cursor cursor = context.getContentResolver().query(FavoriteNewsContract.FavoriteNewsEntry.CONTENT_URI, null, null, null, null);
            //body = parseToArticleObject(cursor);

            return body;

        } catch (Exception e) {
            Log.e(TAG, "Failed to load data.");
            e.printStackTrace();

            return null;

        }
    }


    @Override
    public void onDataSetChanged() {
        GetFavoriteArticles();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {

        if(body != null) {
            int x = body.size();
            return x;
        }else{
            return 0;
        }

    }

    @Override
    public RemoteViews getViewAt(int i) {

        //setting up articles list item
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_listitem);
        try {
            Bitmap bitmap = Picasso.with(context).load(body.get(i).getImage()).get();
            rv.setImageViewBitmap(R.id.articleImageView, bitmap);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error", "Error adding bitmap");
        }
        rv.setTextViewText(R.id.titleTV, body.get(i).getTitle());


        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {

        return 1;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private class LoadWidgetDataTask extends AsyncTask<Cursor, Void, Cursor>{

        @Override
        protected Cursor doInBackground(Cursor... cursors) {

            Cursor cursor = context.getContentResolver().query(FavoriteNewsContract.FavoriteNewsEntry.CONTENT_URI, null, null, null, null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor s) {
            super.onPostExecute(s);
            body = parseToArticleObject(s);

        }
    }
}
