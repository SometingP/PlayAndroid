package com.example.playandroid.fragment.knowledge.model;

import com.example.corelib.entity.ArticleEntity;

/**
 * @author 13973
 */
public interface KnowListener {
   void onFailure(String message);
   void onSucceed(ArticleEntity entity);
}
