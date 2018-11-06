package com.feedhub.someone.feedhub.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by someone on 10/21/18.
 */

public class FeedHubContentProvider extends ContentProvider {

    private ArticleDbHelper dbHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    public static final int FAVORITE_NEWS = 100;
    public static final int FAVORITE_ARTICLE_ID = 101;

    private static UriMatcher buildUriMatcher() {
        /*No matching Uri. Base Case*/
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /*Uri to get all table data*/
        uriMatcher.addURI(FavoriteNewsContract.AUTHORITY, FavoriteNewsContract.PATH_FAVORITE_NEWS, 100);

    /*Uri to get a single item*/
        uriMatcher.addURI(FavoriteNewsContract.AUTHORITY, FavoriteNewsContract.PATH_FAVORITE_NEWS + "/#", FAVORITE_ARTICLE_ID);

        return uriMatcher;

    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new ArticleDbHelper(context);


        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
         /*Reading data from database*/
        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);

        Cursor cursor;
        switch (match){
            case FAVORITE_NEWS:
                cursor = database.query(FavoriteNewsContract.FavoriteNewsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                break;
            default:
                throw new UnsupportedOperationException("Unknown  uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final SQLiteDatabase database = dbHelper.getWritableDatabase();

        Uri returnUri;
        /*match the uri received */
        int match = sUriMatcher.match(uri);
        switch (match){
            case FAVORITE_NEWS:
                /*insert new row of data. If insert was'nt successful it will return -1*/
                long id = database.insert(FavoriteNewsContract.FavoriteNewsEntry.TABLE_NAME, null, contentValues);
                if(id > 0){
                    //success. do something
                    returnUri = ContentUris.withAppendedId(FavoriteNewsContract.FavoriteNewsEntry.CONTENT_URI, id);
                }
                else{
                    //insert has failed
                    throw new android.database.SQLException("Failed to insert row with uri " + uri);
                }

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }

        /*Letting the content resolver know that data has changed and needs to update UI*/
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();

        int rowsDeleted;

        /*match the uri received */
        int match = sUriMatcher.match(uri);
        switch (match) {
            case FAVORITE_ARTICLE_ID:

                String id = uri.getPathSegments().get(1);

                /*delete new row of data. If delete was'nt successful it will return -1*/
                rowsDeleted = database.delete(FavoriteNewsContract.FavoriteNewsEntry.TABLE_NAME, FavoriteNewsContract.FavoriteNewsEntry.COLUMN_ARTICLE_ID + "=?", new String[]{id});

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
          /*notify that a change*/
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;

        }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
