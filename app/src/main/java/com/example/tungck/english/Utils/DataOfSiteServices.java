package com.example.tungck.english.Utils;

import com.example.tungck.english.models.Words;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by TungCK on 10/31/2015.
 */
public interface DataOfSiteServices {
    @GET("api/web/site/randomword")
    Call<Words> GetWord();

}
