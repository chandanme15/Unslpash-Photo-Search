package com.assign.photosearch.userInterface.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.assign.photosearch.R;
import com.assign.photosearch.model.Result;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.assign.photosearch.utils.Constants.KEY_RESULT;

public class ImageViewActivity extends AppCompatActivity {

    @BindView(R.id.avatarImg)
    ImageView mImageView;
    @BindView(R.id.portfolioLink)
    TextView mPortfolioLink;
    @BindView(R.id.instagramLink)
    TextView mInstagramLink;
    @BindView(R.id.name_entry)
    TextView mNameEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);
        if(getIntent().hasExtra(KEY_RESULT)) {
            Result result = (Result) getIntent().getSerializableExtra(KEY_RESULT);
            if(result != null) {
                mNameEntry.setText(result.getUsers().getName());

                Picasso.get().load(result.getUrls().getRegular()).into(mImageView);

                String portfolioLink = result.getUsers().getPortfolio_url();
                mPortfolioLink.setClickable(true);
                mPortfolioLink.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='" + portfolioLink + "'></a>";
                mPortfolioLink.setText(Html.fromHtml(text));

                mInstagramLink.setText(result.getUsers().getInstagramUsername());
            }
        }
    }
}
