package com.example.tungck.english;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tungck.english.Utils.Constants;
import com.example.tungck.english.Utils.DataDownloadListener;
import com.example.tungck.english.Utils.DataOfSiteServices;
import com.example.tungck.english.Utils.FunctionUtils;
import com.example.tungck.english.Utils.GetDataAsyntask;
import com.example.tungck.english.Utils.ImageDownloadListener;
import com.example.tungck.english.models.WordObject;
import com.example.tungck.english.models.Words;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class LearnEngLish extends AppCompatActivity {


    private ImageView imageView;
    private EditText editText;
    private Button btCancel;
    private Button btNext;
    private Button btGetAnswer;
    private String word = "";
    private int countNextClick = 0;
    public boolean isAddScore = true;
    FunctionUtils fu = new FunctionUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn_eng_lish);
        imageView = (ImageView)findViewById(R.id.image);
        editText = (EditText)findViewById(R.id.text1);
        btCancel = (Button)findViewById(R.id.cancel_action);
        btNext = (Button)findViewById(R.id.next_action);
        btGetAnswer = (Button)findViewById(R.id.get_answer_action);
        getData();
        btGetAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fu.showAlertDialog("Kết quả","Từ tương ứng với bức ảnh là: " + word,LearnEngLish.this);
                isAddScore = false;
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClick();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelClick();
            }
        });

    }
    private void nextClick(){
        countNextClick += 1;

        if(editText.getText().toString().trim().toUpperCase().equals(word.toUpperCase())){
            int score = 0;
            if(countNextClick == 1){
                score = 3;
            }else
            if(countNextClick < 3 && countNextClick > 2){
                score = 1;
            }
            if(score > 0 && isAddScore){
                FunctionUtils.showProgressDialog(LearnEngLish.this);
                Gson gson = new GsonBuilder()
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                DataOfSiteServices service = retrofit.create(DataOfSiteServices.class);
                Call<Words> wordsCall = service.AddScore(word, score);
                wordsCall.enqueue(new Callback<Words>() {
                    @Override
                    public void onResponse(Response<Words> response, Retrofit retrofit) {
                        Log.d("LEARNING_ENGLISH","Từ trả về: " + response.isSuccess());
                        FunctionUtils.hideProgressDialog();
                        getData();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        fu.showAlertDialog("Xảy ra lỗi!", "Có lỗi xảy ra trong quá trình thêm điểm", LearnEngLish.this);
                    }
                });
            }else {
                getData();
            }

        }else {
            fu.showAlertDialog("Rất tiếc!", "Câu trả lời của bạn là sai: ", this);
        }
    }

    private void cancelClick(){
        getData();
    }
    private void getData(){
        GetDataAsyntask getData = new GetDataAsyntask(this);
        getData.setDataDownloadListener(new DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(String keySearch, String imageUrl) {
                GetImage im = new GetImage(keySearch);
                final String temp = keySearch;
                im.setImageDownloadListener(new ImageDownloadListener() {
                    @Override
                    public void imageDownloadedSuccessfully(WordObject data) {
                        System.out.println("URL: " + data.getText());
                        imageView.setImageBitmap(data.getBm());
                        word = temp;
                        editText.setText("");
                        isAddScore = true;
                        countNextClick = 0;
                    }

                    @Override
                    public void imageDownloadFailed(int errorCode, String errorMessage) {
                        fu.showAlertDialog("Xảy ra lỗi","Xảy ra lỗi trong quá trình lấy dữ liệu",LearnEngLish.this);
                    }
                });
                im.execute(imageUrl);
            }

            @Override
            public void dataDownloadFailed(int errorCode, String errorMessage) {

            }
        });
        getData.execute();
    }

    public class GetImage extends AsyncTask<String, String, Bitmap> {
        String wordText;
        private ImageDownloadListener imageDownloadListener;
        public  GetImage(String word){
            this.wordText = word;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            FunctionUtils.showProgressDialog(LearnEngLish.this);
        }

        @Override
        protected void onPostExecute(Bitmap bm) {
            super.onPostExecute(bm);
            if(bm != null){
                imageDownloadListener.imageDownloadedSuccessfully(new WordObject(wordText, bm));
            }else {
                imageDownloadListener.imageDownloadFailed(1, "Lỗi trong quá trình tải ảnh");
            }
            FunctionUtils.hideProgressDialog();

        }
        public void setImageDownloadListener(ImageDownloadListener imageDownloadListener) {
            this.imageDownloadListener = imageDownloadListener;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            String imageUrl = params[0];
            System.out.println("error params: " + imageUrl);
            Bitmap bm = null;
            try {
                bm = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
            }catch (IOException e){
                System.out.println("aaaaaaaaaaaa: " + e.getMessage());
                e.printStackTrace();
            }
            return bm;
        }
    }

}
