package com.example.tungck.english.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


import com.example.tungck.english.models.GoogleImageSearchRespone;
import com.example.tungck.english.models.Result;
import com.example.tungck.english.models.WordObject;
import com.example.tungck.english.models.Words;

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
public class GetDataAsyntask extends AsyncTask<Void, Void, WordObject> {

    private String url;
    private FunctionUtils utils;
    private Context context;
    private DataDownloadListener dataDownloadListener;
    WordObject wordObject;
    public GetDataAsyntask(String url, Context context) {
        this.url = url;
        this.context = context;
        utils = new FunctionUtils();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        utils.showProgressDialog(context);
    }

    @Override
    protected void onPostExecute(WordObject arg0) {
        super.onPostExecute(arg0);

        if(arg0 != null)
        {
            System.out.println("error1: "+arg0.getText());
            dataDownloadListener.dataDownloadedSuccessfully(arg0);
        }
        else{
            System.out.println("error2: ");
            dataDownloadListener.dataDownloadFailed();
        }

        utils.hideProgressDialog();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected WordObject doInBackground(Void... voids) {
        wordObject = null;

            System.out.println("error11111: ");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            DataOfSiteServices service = retrofit.create(DataOfSiteServices.class);
            int i = 1;
            System.out.println("error2222: ");
            Call<Words> wordsCall = service.GetWord();
            System.out.println("error3333: ");
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
                        Call<GoogleImageSearchRespone> googleImageSearchRespone1 = service2.GetImage(word.getWord(), 1);
                        googleImageSearchRespone1.enqueue(new Callback<GoogleImageSearchRespone>() {
                            @Override
                            public void onResponse(Response<GoogleImageSearchRespone> response, Retrofit retrofit) {
                                System.out.println(retrofit.baseUrl());
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

                                        String imageUrl = result.getUrl();
                                        System.out.println("URL: " + imageUrl);
                                        try {
                                            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
                                            if(bitmap != null){
                                                wordObject = new WordObject(word.getWord(),bitmap);
                                            }else {
                                                wordObject = new WordObject(Constants.ERROR_CODE_GOOGLE_DATA, "Lỗi trong quá trình load ảnh: " + imageUrl);
                                            }

                                        }catch (MalformedURLException e){
                                            System.out.println("error1: " + e.getMessage());
                                            wordObject = new WordObject(Constants.ERROR_CODE_GOOGLE_DATA, "URL ảnh không đúng định dạng: " + imageUrl);
                                        }
                                        catch (IOException e){
                                            System.out.println("error2: " + e.getMessage());
                                            wordObject = new WordObject(Constants.ERROR_CODE_GOOGLE_DATA, "URL ảnh không đúng định dạng: " + imageUrl);
                                        }

                                    }
                                }else {
                                    wordObject = new WordObject(Constants.ERROR_CODE_GOOGLE_DATA, "Không tìm thấy ảnh nào tương ướng với ký tự: " + word.getWord());
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                System.out.println("error Failure ww: " + t.getMessage());
                            }

                        });



                    }else {
                        wordObject = new WordObject(Constants.ERROR_CODE_MY_DATA, "Dữ liệu không được trả về");
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("error Failure: " + t.getMessage());


                }
            });





        return wordObject;
    }

    public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }


    private WordObject getData(){

        return wordObject;
    }
}
