package com.example.tungck.english.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungCK on 10/31/2015.
 */
public class GoogleImageSearchRespone {
    @SerializedName("responseData")
    @Expose
    public ResponseData responseData;
    @SerializedName("responseStatus")
    @Expose
    public int responseStatus;

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
