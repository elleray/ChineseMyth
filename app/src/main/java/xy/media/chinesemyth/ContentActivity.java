package xy.media.chinesemyth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

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

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.content_tv)
    TextView contentTv;

    private String mTitle, mContent,mTxtFileName;

    public static void startActivity(Activity activity, StoryModel storyModel) {
        Intent intent = new Intent();
        intent.setClass(activity, ContentActivity.class);
        intent.putExtra(EXTRA_TITLE, storyModel.getTitle());
        intent.putExtra(EXTRA_CONTENT, storyModel.getContent());
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

            mContent = FileUtil.getTxt(this, mTxtFileName);
        }
    }

    private void setView() {
        titleTv.setText(mTitle);
        contentTv.setText(mContent);
    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }
}
