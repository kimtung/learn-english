package com.example.tungck.english.models;

/**
 * Created by TungCK on 10/31/2015.
 */
public class Words {
    //http://nguyenhaiha.byethost10.com/website_extension_word/web/index.php/site/randomword?ckattempt=1
    private boolean success;
    private String word;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Words(boolean success, String word) {
        this.success = success;
        this.word = word;
    }
    public Words() {
    }
}
