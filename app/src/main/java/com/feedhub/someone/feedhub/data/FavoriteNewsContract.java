package com.feedhub.someone.feedhub.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by someone on 10/21/18.
 */

public class FavoriteNewsContract {

    /*Building Uri to access data from the db*/
    public static final String AUTHORITY = "com.feedhub.someone.feedhub";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_FAVORITE_NEWS = "favorite_news";


    /*Base columns generate automatically the id column*/
    public static final class FavoriteNewsEntry implements BaseColumns {

        /*Building Uri.  Final Uri should be
        content://com.feedhub.someone.feedhub/favorite_news*/
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_NEWS).build();

        /*Modeling database table*/
        public static final String TABLE_NAME = "favorite_news";
        public static final String COLUMN_ARTICLE_ID = "article_id";
        public static final String COLUMN_ARTICLE_TITLE = "article_title";
        public static final String COLUMN_ARTICLE_DATE = "article_date";
        public static final String COLUMN_ARTICLE_DESCRIPTION = "article_description";
        public static final String COLUMN_ARTICLE_LINK = "article_link";
        public static final String COLUMN_ARTICLE_IMAGE = "article_image";
    }
}