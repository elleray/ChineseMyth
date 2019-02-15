package xy.media.chinesemyth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xy.media.chinesemyth.model.StoryModel;

/**
 * Created by Yangwenjie on 2019/2/14.
 */

public class StoryViewHolder extends RecyclerView.ViewHolder  {

    @BindView(R.id.story_cover_iv)
    public ImageView mIvCover;
    @BindView(R.id.story_title_tv)
    TextView mTvTitle;

    private Context mContext;
    private View rootView;

    private StoryModel storyModel;

    public StoryViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.rootView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void setData(StoryModel model) {
        storyModel = model;
    }

    public void displayData(int pos, StoryModel model) {
        mTvTitle.setText((pos+1) +"." + model.getTitle());
    }
}
