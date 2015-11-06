package com.example.tungck.english;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tungck.english.Utils.Constants;
import com.example.tungck.english.Utils.DataDownloadListener;
import com.example.tungck.english.Utils.FunctionUtils;
import com.example.tungck.english.Utils.GetDataAsyntask;
import com.example.tungck.english.models.WordObject;

public class LearnEngLish extends AppCompatActivity {


    private ImageView imageView;
    private EditText editText;
    private Button btCancel;
    private Button btNext;
    private String word;
    FunctionUtils fu = new FunctionUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn_eng_lish);
        imageView = (ImageView)findViewById(R.id.image);
        editText = (EditText)findViewById(R.id.text1);
        btCancel = (Button)findViewById(R.id.cancel_action);
        btNext = (Button)findViewById(R.id.next_action);
        getData();
        System.out.println("ok");
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
        if(editText.getText().toString().trim().equals(word)){
            fu.showAlertDialog("Lỗi","Câu trả lời của bạn là sai",this);
        }
    }

    private void cancelClick(){
        getData();
    }
    private void getData(){
        GetDataAsyntask getData = new GetDataAsyntask(Constants.BASE_URL, this);
        getData.setDataDownloadListener(new DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(WordObject data) {
                imageView.setImageBitmap(data.getBm());
                word = data.getText();
                System.out.println("success");

            }

            @Override
            public void dataDownloadFailed() {

            }
        });
        getData.execute();
    }

}
