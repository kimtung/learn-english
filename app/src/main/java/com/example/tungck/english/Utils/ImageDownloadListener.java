package com.example.tungck.english.Utils;

import com.example.tungck.english.models.WordObject;

/**
 * Created by TungCK on 11/7/2015.
 */
public interface ImageDownloadListener {
    void imageDownloadedSuccessfully(WordObject data);
    void imageDownloadFailed(int errorCode, String errorMessage);
}