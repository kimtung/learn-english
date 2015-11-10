package com.example.tungck.english.Utils;

import com.example.tungck.english.models.Words;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by TungCK on 10/31/2015.
 */
public interface DataOfSiteServices {
    @GET("api/web/site/randomword")
    Call<Words> GetWord();

    @FormUrlEncoded
    @POST("api/web/site/addscore")
    Call<Words> AddScore(@Field("word") String word, @Field("score") int score);

}
