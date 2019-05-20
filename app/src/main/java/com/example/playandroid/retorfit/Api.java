package com.example.playandroid.retorfit;

import com.example.corelib.entity.ArticleEntity;
import com.example.corelib.entity.BannerEntity;
import com.example.corelib.entity.HotKeysEntity;
import com.example.corelib.entity.KnowlegeEntity;
import com.example.corelib.entity.OffciaAccountEntity;
import com.example.corelib.entity.OffcialAccountTitleEntity;
import com.example.corelib.entity.SearchDataEntity;
import com.example.corelib.entity.WebsiteEntity;
import com.example.playandroid.collection.CollectionArticleEntity;
import com.example.playandroid.fragment.project.ProjectEntity;
import com.example.playandroid.fragment.project.ProjectTitleEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    /**
     * 获取Banner展示图片
     */
    @GET("banner/json")
    Call<BannerEntity> getBannerData();

    @GET("article/list/{page}/json")
    Call<ArticleEntity> getHomeArticleList(@Path("page") int page);

    @GET("tree/json")
    Call<KnowlegeEntity> getKnowlegeDataList();

    @GET("/article/list/0/json")
    Call<ArticleEntity> getKnowlegeItemDataList(@Query("cid") int id);

    @GET("friend/json")
    Call<WebsiteEntity> getWebsiteDatas();

    @GET("hotkey/json")
    Call<HotKeysEntity> getHotKeys();

    @POST("article/query/0/json")
    Call<SearchDataEntity> getSearchData(@Query("k") String k);

    @GET("wxarticle/chapters/json")
    Call<OffcialAccountTitleEntity> getOffciaAccountTitle();

    @GET("wxarticle/list/{id}/1/json")
    Call<OffciaAccountEntity> getOffciaAccountDatas(@Path("id") int id);

    @GET("project/list/1/json")
    Call<ProjectEntity> getProjectDatas(@Query("cid") int id);

    @GET("project/tree/json")
    Call<ProjectTitleEntity> getProjectTitles();

    @GET("wxarticle/list/{cid}/1/json")
    Call<OffciaAccountEntity> getOffciaSearchDatas(@Path("id") int cid, @Query("k") String data);

    @POST("lg/collect/{id}/json")
    Call<ResponseBody> setCollectionArticle(@Path("id") int id);

    @GET("lg/collect/list/0/json")
    Call<CollectionArticleEntity> getCollectionArticleListData();

    @POST("lg/uncollect_originId/{id}/json")
    Call<ResponseBody> setClearCollectionArticle(@Path("id") int id);
}
