package com.assign.photosearch.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.assign.photosearch.model.Result;
import com.assign.photosearch.model.ResultModel;
import com.assign.photosearch.repository.Repository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private Repository repository;
    private MutableLiveData<List<Result>> apiData;
    private MutableLiveData<Boolean> error;

    MainViewModel(Repository repository){
        this.repository = repository;
        apiData = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public MutableLiveData<List<Result>> getApiData() {
        return apiData;
    }

    public MutableLiveData<Boolean> getError() {
        return error;
    }

    public void loadRecords(String photoText){
        repository.updateApiQueryMap(photoText);
        repository.add(
                repository.getRecordsFromAPI()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(ResultModel::getResults)
                        .subscribe(new Consumer<List<Result>>() {
                                       @Override
                                       public void accept(List<Result> recordList) throws Exception {
                                           if (recordList != null && !recordList.isEmpty()) {
                                               apiData.postValue(recordList);
                                               error.postValue(false);
                                           } else {
                                               error.postValue(true);
                                           }
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable error) throws Exception {
                                           MainViewModel.this.error.postValue(true);
                                       }
                                   }
                        )
        );
    }

    public void onClear(){
        repository.dispose();
    }

}
