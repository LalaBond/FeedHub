package com.feedhub.someone.feedhub;

import com.feedhub.someone.feedhub.model.ArticleSerializableModel;
import com.prof.rssparser.Article;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by someone on 10/4/18.
 */

public class Helper {

//    public final HelperMethod(){
//
//
//
//    }
public static ArticleSerializableModel ArticleToArticleSerializableModelConverter(Article article){
        ArticleSerializableModel articleSerializableList = new ArticleSerializableModel();

        try {

            articleSerializableList.setTitle(article.getTitle());
            articleSerializableList.setAuthor(article.getAuthor());
            articleSerializableList.setContent(article.getContent());
            articleSerializableList.setDescription(article.getDescription());
            articleSerializableList.setImage(article.getImage());
            articleSerializableList.setLink(article.getLink());
            articleSerializableList.setPubDate(article.getPubDate());
            articleSerializableList.setCategories(article.getCategories());

        }catch(Exception e) {
            e.printStackTrace();
        }
    return articleSerializableList;
}

}
