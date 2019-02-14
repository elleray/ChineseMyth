package xy.media.chinesemyth.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    protected List<T> mListData;

    public BaseRecyclerViewAdapter(Context context, List<T> listData) {
        this.mContext = context;
        this.mListData = listData;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {}

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    public T getItem(int position) {
        if (position >= 0 && position < getItemCount()) {
            return mListData.get(position);
        }
        return null;
    }

    public void resetData(List<T> listData) {
        this.mListData = listData;
        notifyDataSetChanged();
    }

    public List<T> getListData() {
        return mListData;
    }

}
