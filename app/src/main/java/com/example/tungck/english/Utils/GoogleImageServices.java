package com.example.tungck.english.Utils;

import com.example.tungck.english.models.GoogleImageSearchRespone;

import retrofit.Call;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by TungCK on 11/4/2015.
 */
public interface GoogleImageServices {

    /**
     * @return a
     */
    @GET("ajax/services/search/images")
    Call<GoogleImageSearchRespone> GetImage(@Query("q") String key, @Query("v") String version);
}
