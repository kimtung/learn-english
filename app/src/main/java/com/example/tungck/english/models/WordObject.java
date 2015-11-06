package com.example.tungck.english.models;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by TungCK on 11/4/2015.
 */
public class WordObject {
    private int point;
    private Bitmap bm;
    private String text;

    private int codeError;
    private String errorMessage;

    public int getCodeError() {
        return codeError;
    }

    public void setCodeError(int codeError) {
        this.codeError = codeError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }
    public WordObject(){

    }


    public WordObject(String text, Bitmap bm){
        this.text = text;
        this.bm = bm;
        codeError = 0;

    }
    public WordObject(int codeError, String errorMessage){
        this.errorMessage = errorMessage;
        this.codeError = codeError;

    }
    public WordObject(String text, Bitmap bm, int point){
        this.text = text;
        this.bm = bm;
        this.point = point;
        codeError = 0;
    }
}
