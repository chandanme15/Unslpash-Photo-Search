package com.assign.photosearch.webService.service;

import android.content.Context;

import com.assign.photosearch.model.ResultModel;
import com.assign.photosearch.webService.rest.RestClient;

import java.util.Map;

import io.reactivex.Observable;

public class APIService {
    private RestClient restClient;

    public APIService(Context context){
        restClient = new RestClient();
    }


    public Observable<ResultModel> getRecords(Map<String, String> map){
        return restClient.getApi().getRecords(map);
    }
}
