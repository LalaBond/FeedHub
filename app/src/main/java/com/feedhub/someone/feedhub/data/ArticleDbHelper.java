package com.feedhub.someone.feedhub.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by someone on 10/21/18.
 */

public class ArticleDbHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "feedhub.db";

    private static final int DATABASE_VERSION = 1;

    public ArticleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         /*Query for creating database*/
        final String SQL_CREATE_NEWSFAVORITES_TABLE =

                "CREATE TABLE " + FavoriteNewsContract.FavoriteNewsEntry.TABLE_NAME + " (" +

                        //FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_ID + " TEXT PRIMARY KEY, " +
                        FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_TITLE + " TEXT NOT NULL, " +
                        FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_DATE + " TEXT NOT NULL, " +
                        FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_DESCRIPTION + " TEXT NOT NULL, " +
                        FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_LINK + " TEXT NOT NULL, " +
                        FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_IMAGE + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_NEWSFAVORITES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //this only gets called if i change the db version
    }
}
