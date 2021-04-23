package com.assign.photosearch.userInterface.base;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.assign.photosearch.viewModel.BaseViewModel;


public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {
    protected T viewModel;
    public abstract T getViewModel();

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        viewModel = getViewModel();
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }
}
