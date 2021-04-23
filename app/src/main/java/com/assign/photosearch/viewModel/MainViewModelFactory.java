package com.assign.photosearch.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.assign.photosearch.repository.Repository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public MainViewModelFactory(Repository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if ( modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown view model class");
    }
}
