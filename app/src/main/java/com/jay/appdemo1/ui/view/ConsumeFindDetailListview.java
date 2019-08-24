package com.jay.appdemo1.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by liuzhi on 2019/2/13.
 */

public class ConsumeFindDetailListview extends ListView {

    private float down;

    public ConsumeFindDetailListview(Context context) {
        super(context);
        init(context);
    }

    public ConsumeFindDetailListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ConsumeFindDetailListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                down = getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = getY();
                float move = y - down;
                if (move<0) {
                    if (move+(getChildAt(0).getMeasuredHeight() - getChildAt(0).getHeight() + getChildAt(0).getTop())<=0) {

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
