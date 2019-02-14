package xy.media.chinesemyth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xy.media.chinesemyth.adapter.StoryListAdapter;
import xy.media.chinesemyth.model.StoryListBean;
import xy.media.chinesemyth.model.StoryModel;
import xy.media.chinesemyth.view.ScrollDetectRecyclerView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_rv)
    ScrollDetectRecyclerView listRv;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setInitialPrefetchItemCount(5);
        listRv.setLayoutManager(mLayoutManager);
        listRv.setItemAnimator(null);
        listRv.setScrollCallback(new ScrollDetectRecyclerView.ScrollCallback() {
            @Override
            public void onScrollToBottom() {
//                mPresenter.loadMoreShares();
            }

            @Override
            public void onScrollStateChanged(int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING: {
                        Glide.with(MainActivity.this).pauseRequests();
                        break;
                    }
                    case RecyclerView.SCROLL_STATE_SETTLING: {
                        Glide.with(MainActivity.this).pauseRequests();
                        break;
                    }
                    case RecyclerView.SCROLL_STATE_IDLE: {
                        Glide.with(MainActivity.this).resumeRequests();
                        break;
                    }
                }
            }
        });
    }

    private void initData() {
        //得到本地json文本内容
        String fileName = "story.json";
        String foodJson = LocalJsonResolutionUtils.getJson(this, fileName);
        //转换为对象
        StoryListBean list = LocalJsonResolutionUtils.JsonToObject(foodJson, StoryListBean.class);

        if (list != null && list.getList() != null) {
            StoryListAdapter adapter = new StoryListAdapter(this, list.getList());
            listRv.setAdapter(adapter);
        }
    }
}
