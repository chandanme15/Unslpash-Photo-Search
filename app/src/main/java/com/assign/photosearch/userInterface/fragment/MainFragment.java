package com.assign.photosearch.userInterface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assign.photosearch.Application;
import com.assign.photosearch.R;
import com.assign.photosearch.adapter.MainAdapter;
import com.assign.photosearch.model.Result;
import com.assign.photosearch.repository.Repository;
import com.assign.photosearch.userInterface.activity.ImageViewActivity;
import com.assign.photosearch.userInterface.base.BaseFragment;
import com.assign.photosearch.utils.Constants;
import com.assign.photosearch.utils.Util;
import com.assign.photosearch.viewModel.MainViewModel;
import com.assign.photosearch.viewModel.MainViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.assign.photosearch.utils.Constants.KEY_RESULT;

public class MainFragment extends BaseFragment<MainViewModel> implements MainAdapter.SubItemClickListner {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private EditText etSearch;
    private Button btSearch;
    private View mView;
    private MainAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public MainViewModel getViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(Repository.getNewInstance(Application.getInstance()));
        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, mView);
        initView();
        initMembers();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
        setLiveDataObserver();
        if(Util.isNetworkAvailable(Application.getInstance())) {
            showLoading(true);
            showError(View.GONE);
            LoadRecordsFromAPI("nature");
        }
        else {
            mAdapter.clearData();
            showError(View.VISIBLE);
        }
    }

    private void initMembers() {
        Constants.PAGE_COUNT = 1;
        showLoading(false);
        showError(View.GONE);
    }

    private void setLiveDataObserver() {
        viewModel.getApiData().observe(getViewLifecycleOwner(), recordList -> {
            if (Constants.PAGE_COUNT == 1) {
                mAdapter.clearData();
                displaySnackbar(false, getString(R.string.data_updated));
            }
            mAdapter.addData(recordList);
            showLoading(false);
            showError(View.GONE);
        });
        viewModel.getError().observe(getViewLifecycleOwner(), isError -> {
            if (isError) {
                displaySnackbar(true, getString(R.string.cant_load_records));
                showLoading(false);
                if (mAdapter.getData().isEmpty()) {
                    showError(View.VISIBLE);
                }
            }
        });
    }

    public void LoadRecordsFromAPI(String photoText) {
        viewModel.loadRecords(photoText);
    }

    private void initView() {
        if (getActivity() != null) {
            etSearch = getActivity().findViewById(R.id.et_search);
            btSearch = getActivity().findViewById(R.id.bt_search);
            btSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String photoText = etSearch.getText().toString().trim();
                    if(TextUtils.isEmpty(photoText)) {
                        displaySnackbar(true, "Search field empty!!");
                        return;
                    }
                    if(Util.isNetworkAvailable(Application.getInstance())) {
                        showLoading(true);
                        showError(View.GONE);
                        LoadRecordsFromAPI(photoText);
                    }
                    else {
                        mAdapter.clearData();
                        showError(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void setupRecycler() {
        mLayoutManager = new LinearLayoutManager(Application.getInstance());
        mAdapter = new MainAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        int scrollPosition = 0;
        mRecyclerView.scrollToPosition(scrollPosition);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Application.getInstance(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void showLoading(boolean refresh) {
        if (getActivity() != null) {
            getActivity().findViewById(R.id.sample_main_layout).findViewById(R.id.loading).setVisibility(refresh ? View.VISIBLE : View.GONE);
        }
    }

    private void showError(int Visibility) {
        if (getActivity() != null) {
            getActivity().findViewById(R.id.sample_main_layout).findViewById(R.id.error).setVisibility(Visibility);
        }
    }

    private void displaySnackbar(boolean isError, String message) {
        Util.showSnack(mView, isError, message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onClear();
    }

    @Override
    public void onClickItemListener(Result data) {
        Intent intent = new Intent(getActivity(), ImageViewActivity.class);
        intent.putExtra(KEY_RESULT, data);
        startActivity(intent);
    }
}



