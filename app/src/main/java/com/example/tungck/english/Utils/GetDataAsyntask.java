package com.example.tungck.english.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


import com.example.tungck.english.models.GoogleImageSearchRespone;
import com.example.tungck.english.models.Result;
import com.example.tungck.english.models.WordObject;
import com.example.tungck.english.models.Words;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 * Created by TungCK on 10/31/2015.
 */
public class GetDataAsyntask extends AsyncTask<Void, Void, Void> {
    private FunctionUtils utils;
    private Context context;

    private String imageUrl = null;
    private String wordText = null;

    private int errorCode = 0;
    private String errorMessage = null;

    private DataDownloadListener dataDownloadListener;
    public GetDataAsyntask(Context context) {
        this.context = context;
        utils = new FunctionUtils();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        FunctionUtils.showProgressDialog(context);
    }

    @Override
    protected void onPostExecute(Void arg0) {
        super.onPostExecute(arg0);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Gson gson = new GsonBuilder()
                .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            DataOfSiteServices service = retrofit.create(DataOfSiteServices.class);
            Call<Words> wordsCall = service.GetWord();
            wordsCall.enqueue(new Callback<Words>() {
                @Override
                public void onResponse(Response<Words> response, Retrofit retrofit) {
                    final Words word = response.body();
                    if(word != null && word.isSuccess() && word.getWord() != null){
                        System.out.println("error11113: " + word.getWord());
                        Retrofit retrofit2 = new Retrofit.Builder()
                                .baseUrl(Constants.GOOGLE_BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .build();
                        GoogleImageServices service2 = retrofit2.create(GoogleImageServices.class);
                        Call<GoogleImageSearchRespone> googleImageSearchRespone1 = service2.GetImage(word.getWord(), "1.0");
                        googleImageSearchRespone1.enqueue(new Callback<GoogleImageSearchRespone>() {
                            @Override
                            public void onResponse(Response<GoogleImageSearchRespone> response, Retrofit retrofit) {
                                GoogleImageSearchRespone googleImageSearchRespone = response.body();
                                if(googleImageSearchRespone != null && googleImageSearchRespone.getResponseData() != null && googleImageSearchRespone.getResponseData().getResults() != null){
                                    int count = googleImageSearchRespone.getResponseData().getResults().size();
                                    if(count > 0){
                                        if(count > 10){
                                            count = 10;
                                        }
                                        Random rd = new Random();
                                        int index = rd.nextInt(count);
                                        Result result = googleImageSearchRespone.getResponseData().getResults().get(index);
                                        imageUrl = result.getUrl();
                                        System.out.println("error URL: "+imageUrl);
                                        wordText = word.getWord();
                                        dataDownloadListener.dataDownloadedSuccessfully(wordText,imageUrl);
                                    }else{
                                        errorCode = Constants.ERROR_CODE_GOOGLE_DATA;
                                        errorMessage = "Không tìm thấy ảnh nào tương ướng với ký tự: " + word.getWord();
                                        dataDownloadListener.dataDownloadFailed(errorCode, errorMessage);
                                    }
                                }else {
                                    errorCode = Constants.ERROR_CODE_GOOGLE_DATA;
                                    errorMessage = "Xảy ra vấn đề với google api response";
                                    dataDownloadListener.dataDownloadFailed(errorCode, errorMessage);
                                }
//                                utils.hideProgressDialog();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                errorCode = Constants.ERROR_CODE_GOOGLE_DATA;
                                errorMessage = "Dữ liệu google trả về sai định dạng";
//                                utils.hideProgressDialog();
                            }

                        });

                    }else {
                        errorCode = Constants.ERROR_CODE_MY_DATA;
                        errorMessage = "Dữ liệu không được trả về";
//                        utils.hideProgressDialog();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    errorCode = Constants.ERROR_CODE_MY_DATA;
                    errorMessage = "Dữ liệu trả về sai định dạng";
//                    utils.hideProgressDialog();
                }
            });
        return null;
    }

    public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }


}
