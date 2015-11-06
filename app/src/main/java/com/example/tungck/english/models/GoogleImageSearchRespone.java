package com.example.tungck.english.models;

/**
 * Created by TungCK on 10/31/2015.
 */
public class GoogleImageSearchRespone {

    private ResponseData responseData;
    private int responseStatus;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
}
