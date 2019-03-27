package xy.media.chinesemyth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xy.media.chinesemyth.model.StoryModel;

/**
 * Created by elleray on 2019/2/14.
 */

public class ContentActivity extends Activity {

    private final static String EXTRA_TITLE = "story_title";
    private final static String EXTRA_CONTENT = "story_content";
    private final static String EXTRA_IMG = "story_img";

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.cover_iv)
    ImageView mCoverIv;

    private String mTitle, mContent,mTxtFileName, mUrl;

    public static void startActivity(Activity activity, StoryModel storyModel) {
        Intent intent = new Intent();
        intent.setClass(activity, ContentActivity.class);
        intent.putExtra(EXTRA_TITLE, storyModel.getTitle());
        intent.putExtra(EXTRA_CONTENT, storyModel.getContent());
        intent.putExtra(EXTRA_IMG, storyModel.getImgUrl());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);

        initData();
        setView();
    }

    private void initData() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            mTitle = intent.getStringExtra(EXTRA_TITLE);
            mTxtFileName = intent.getStringExtra(EXTRA_CONTENT);
            mUrl = intent.getStringExtra(EXTRA_IMG);

            mContent = FileUtil.getTxt(this, mTxtFileName);
        }
    }

    private void setView() {
        titleTv.setText(mTitle);
        contentTv.setText(mContent);

        if (! TextUtil.isNull(mUrl)) {
            // 优化内存占用
            RequestOptions options = new RequestOptions()
                    .placeholder(R.color.white)
                    .error(R.drawable.ic_default_img);
            TransitionOptions transitionOptions = new DrawableTransitionOptions()
                    .crossFade();

            Glide.with(this).load(mUrl).apply(options)
                    .transition(transitionOptions).into(mCoverIv);
        } else {
            mCoverIv.setImageResource(R.drawable.ic_default_img);
        }
    }

    @OnClick({R.id.back_iv, R.id.share_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv: {
                onBackPressed();
                break;
            }
            case R.id.share_iv: {
                CommonUtil.shareText(this, mContent);
                break;
            }
        }
    }
}
