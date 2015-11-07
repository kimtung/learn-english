package com.example.tungck.english.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungCK on 10/31/2015.
 */
public class Result {

        @SerializedName("GsearchResultClass")
        @Expose
        private String GsearchResultClass;
        @SerializedName("width")
        @Expose
        private String width;
        @SerializedName("height")
        @Expose
        private String height;
        @SerializedName("imageId")
        @Expose
        private String imageId;
        @SerializedName("tbWidth")
        @Expose
        private String tbWidth;
        @SerializedName("tbHeight")
        @Expose
        private String tbHeight;
        @SerializedName("unescapedUrl")
        @Expose
        private String unescapedUrl;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("visibleUrl")
        @Expose
        private String visibleUrl;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("titleNoFormatting")
        @Expose
        private String titleNoFormatting;
        @SerializedName("originalContextUrl")
        @Expose
        private String originalContextUrl;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("contentNoFormatting")
        @Expose
        private String contentNoFormatting;
        @SerializedName("tbUrl")
        @Expose
        private String tbUrl;
//    public String GsearchResultClass;
//    public int width;
//    public int height;
//    public String imageId;
//    public String tbWidth;
//    public String tbHeight;
//    public String unescapedUrl;
//    public String url;
//    public String visibleUrl;
//    public String title;
//    public String titleNoFormatting;
//    public String originalContextUrl;
//    public String content;
//    public String contentNoFormatting;
//    public String tbUrl;


    public String getGsearchResultClass() {
        return GsearchResultClass;
    }

    public void setGsearchResultClass(String gsearchResultClass) {
        GsearchResultClass = gsearchResultClass;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTbWidth() {
        return tbWidth;
    }

    public void setTbWidth(String tbWidth) {
        this.tbWidth = tbWidth;
    }

    public String getTbHeight() {
        return tbHeight;
    }

    public void setTbHeight(String tbHeight) {
        this.tbHeight = tbHeight;
    }

    public String getUnescapedUrl() {
        return unescapedUrl;
    }

    public void setUnescapedUrl(String unescapedUrl) {
        this.unescapedUrl = unescapedUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVisibleUrl() {
        return visibleUrl;
    }

    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    public void setTitleNoFormatting(String titleNoFormatting) {
        this.titleNoFormatting = titleNoFormatting;
    }

    public String getOriginalContextUrl() {
        return originalContextUrl;
    }

    public void setOriginalContextUrl(String originalContextUrl) {
        this.originalContextUrl = originalContextUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNoFormatting() {
        return contentNoFormatting;
    }

    public void setContentNoFormatting(String contentNoFormatting) {
        this.contentNoFormatting = contentNoFormatting;
    }

    public String getTbUrl() {
        return tbUrl;
    }

    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
    }


}

