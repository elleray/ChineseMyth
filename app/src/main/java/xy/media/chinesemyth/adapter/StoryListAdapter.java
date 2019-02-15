package xy.media.chinesemyth.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import xy.media.chinesemyth.ContentActivity;
import xy.media.chinesemyth.R;
import xy.media.chinesemyth.StoryViewHolder;
import xy.media.chinesemyth.model.StoryModel;

/**
 * Created by Yangwenjie on 2019/2/14.
 */

public class StoryListAdapter extends BaseRecyclerViewAdapter<StoryModel> {

    public StoryListAdapter(Context context,  List<StoryModel> shareList) {
        super(context, shareList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoryViewHolder(mContext, View.inflate(mContext, R.layout.item_story, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StoryModel storyListItem = getItem(position);
        if (storyListItem == null ) {
            return;
        }
        StoryViewHolder storyHolder = (StoryViewHolder) holder;
        storyHolder.setData(storyListItem);

        storyHolder.displayData(position, storyListItem);
        setAvatarClickListener(storyHolder.mIvCover, storyListItem);
    }

    /**
     * 查找指定Share的位置
     */
    private int getItemPosition(final StoryModel shareModel) {
        int position = -1;

        if (shareModel == null || mListData == null) {
            return position;
        }

        for (int i = 0; i < mListData.size(); i++) {
            StoryModel itemShareModel = mListData.get(i);
            if (itemShareModel != null) {
                position = i;
                break;
            }
        }

        return position;
    }

    private void setAvatarClickListener(ImageView avatarView, final StoryModel model) {
        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentActivity.startActivity((Activity) mContext, model);
            }
        });
    }

}
