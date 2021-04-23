package com.assign.photosearch.userInterface.activity;

import android.os.Bundle;

import com.assign.photosearch.R;
import com.assign.photosearch.userInterface.base.BaseActivity;
import com.assign.photosearch.userInterface.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.sample_content_fragment, MainFragment.getInstance()).commit();
    }
}