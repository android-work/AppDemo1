package com.jay.appdemo1.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class CricleImageView extends ImageView {

    /*private Paint paint;
    private Matrix matrix;

    public CricleImageView(Context context) {
        this(context,null);
    }

    public CricleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CricleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        matrix = new Matrix();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        Drawable drawable = getDrawable();
        if (drawable!=null){
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas1 = new Canvas(bitmap);
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(bitmapShader);
            int srcle = Math.min(measuredHeight / bitmap.getHeight(), measuredWidth / bitmap.getWidth());
            matrix.setScale(srcle,srcle);
            canvas.drawCircle(measuredHeight/2,measuredHeight/2,measuredHeight/2,paint);
        }else{
            super.onDraw(canvas);
        }
    }*/


        private Bitmap bitmap;
        private int width;
        //    private Paint paint = new Paint();

        public CricleImageView(Context context) {

            this(context, null);
        }

        public CricleImageView(Context context, AttributeSet attrs) {

            this(context, attrs, 0);
        }

        public CricleImageView(Context context, AttributeSet attrs, int defStyle) {

            super(context, attrs, defStyle);
            init();

        }

        Path path;
        public PaintFlagsDrawFilter mPaintFlagsDrawFilter;// 毛边过滤
        Paint paint;

        public void init() {
            mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0,
                    Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setColor(Color.WHITE);

        }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        width = Math.min(measuredHeight, measuredWidth);
        Log.e("tag",measuredHeight+":::"+measuredWidth+"::width::"+width);
        setMeasuredDimension(width,width);
    }*/

        @Override
        protected void onDraw(Canvas cns) {
            // TODO Auto-generated method stub
            float h = getMeasuredHeight();
            float w = getMeasuredWidth();
            Log.e("tag","h:"+h+"\nw:"+w);
            if (path == null) {
                path = new Path();
                path.addCircle(w / 2.0f, h / 2.0f,
                        (float) Math.min(w / 2.0f, (h / 2.0f)), Path.Direction.CCW);
                path.close();
            }
            cns.drawCircle(w / 2.0f, h / 2.0f, Math.min(w / 2.0f, h / 2.0f), paint);
            int saveCount = cns.getSaveCount();
            cns.save();
            cns.setDrawFilter(mPaintFlagsDrawFilter);
            cns.clipPath(path, Region.Op.REPLACE);
            cns.setDrawFilter(mPaintFlagsDrawFilter);
            cns.drawColor(Color.WHITE);
            super.onDraw(cns);
            cns.restoreToCount(saveCount);
        }



}
