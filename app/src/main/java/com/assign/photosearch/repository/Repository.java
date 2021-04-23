package com.assign.photosearch.repository;

import android.content.Context;

import com.assign.photosearch.model.Result;
import com.assign.photosearch.model.ResultModel;
import com.assign.photosearch.utils.Constants;
import com.assign.photosearch.webService.service.APIService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class Repository {

    private APIService apiService;
    private CompositeDisposable disposable;
    private Map<String, String> queryMap;

    public static Repository getNewInstance(Context context) {
        return new Repository(context);
    }

    private Repository(Context context) {
        apiService = new APIService(context);
        disposable = new CompositeDisposable();
        queryMap = new HashMap<>();
        initApiQueryMap();
    }

    public Observable<ResultModel> getRecordsFromAPI(){
        return apiService.getRecords(queryMap);
    }

    public void dispose() {
        if(disposable != null ) disposable.dispose();
    }

    public void add(Disposable disposable) {
        if(this.disposable != null ) this.disposable.add(disposable);
    }

    public void initApiQueryMap(){
        queryMap.put("order_by","relevant");
        queryMap.put("page",String.valueOf(Constants.PAGE_COUNT));
        queryMap.put("per_page","50");
        queryMap.put("client_id", "BYDdlmiM8TSExLzufjvh-Jr7opPRbCdC3N3nYRVs-Dk");
    }

    public void updateApiQueryMap(String photoText){
        queryMap.put("query",photoText);
    }
}
