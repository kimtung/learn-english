package com.example.tungck.english.Utils;

import com.example.tungck.english.models.WordObject;

/**
 * Created by TungCK on 11/4/2015.
 */
public interface DataDownloadListener {
    void dataDownloadedSuccessfully(WordObject data);
    void dataDownloadFailed();
}
