package com.assign.photosearch.webService.rest;

import com.assign.photosearch.model.ResultModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RestApi {

    @GET("/search/photos")
    Observable<ResultModel> getRecords(@QueryMap Map<String, String> filter);
}
