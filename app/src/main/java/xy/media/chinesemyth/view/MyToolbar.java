package xy.media.chinesemyth.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 */

public class MyToolbar extends Toolbar {
    public MyToolbar(Context context) {
        super(context);
        init();
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setContentInsetsAbsolute(0, 0);
    }

}
