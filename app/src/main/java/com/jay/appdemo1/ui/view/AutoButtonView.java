package com.jay.appdemo1.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.jay.appdemo1.R;

/**
 * Created by liuzhi on 2019/3/30.
 */

public class AutoButtonView extends ViewGroup {

    private int widthModel;
    private int heightModel;
    private int widthSize;
    private int heightSize;
    private float horizontalSpace;
    private float vertivalSpace;
    private int rawIndex;
    private int finalWidth;

    public AutoButtonView(Context context) {
        this(context,null);
    }

    public AutoButtonView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AutoButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义样式
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.auto_style);
        //获取子控件之间横竖间距
        horizontalSpace = typedArray.getDimension(R.styleable.auto_style_horizontalSpace, 0);
        vertivalSpace = typedArray.getDimension(R.styleable.auto_style_vertivalSpace, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取自定义控件的宽度及模式
        widthModel = MeasureSpec.getMode(widthMeasureSpec);
        heightModel = MeasureSpec.getMode(heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //孩子控件的列计数器
        int rawIndex = 0;

        //自定义控件的总宽度
        int width = 0;
        int height = 0;

        //孩子控件的累加宽度
        int rowWidth = 0;
        //孩子控件的累加高度
        int rowHeight = 0;

        //获取自定义控件的孩子控件个数
        int childCount = getChildCount();

        //循环遍历每个孩子控件并获取其宽高来计算父控件宽高
        for (int i=0;i<childCount;i++){

            //获取每个孩子控件
            View childView = getChildAt(i);

            //如果当前的孩子控件被GONE掉，则跳过取下一个控件
            if (childView.getVisibility()==GONE){

                //如果最有一个孩子控件被gone掉，则汇总父控件的宽高
                if (i==childCount-1){
                    width = Math.max(rowWidth,width);
                    height += rowHeight;
                }
                continue;
            }

            //测量自定义控件中每个子控件的宽度，包括padding值
            measureChildWithMargins(childView,widthMeasureSpec,0,heightMeasureSpec,0);

            //获取孩子控件的宽高
            int childViewHeight = childView.getMeasuredHeight();
            int childViewWidth = childView.getMeasuredWidth();

            //获取孩子控件的左右margin值
            MarginLayoutParams clidLp = (MarginLayoutParams) childView.getLayoutParams();
            int childLeftMargin = clidLp.leftMargin;
            int childRightMargin = clidLp.rightMargin;

            int childMarginWidth = childLeftMargin + childRightMargin + childViewWidth;

            //判断每遍历一个孩子控件后，累加其宽是否超出屏幕
            if (childMarginWidth + rowWidth + (rawIndex > 0 ? horizontalSpace : 0 ) > widthSize - getPaddingLeft() - getPaddingRight()){

                //取出最宽的一行做为控件的宽
                width = Math.max(rowWidth, width);//父控件的宽度

                //保存本次的孩子控件的宽度
                rowWidth = childMarginWidth;

                //父亲控件高度增加一行最高的孩子控件
                height = (int) (rowHeight+vertivalSpace);

                //列的索引归0
                rawIndex = 0;

            }else{

                if (rawIndex >0){
                    rowWidth += childMarginWidth + horizontalSpace;//行的累加宽度
                }else{
                    rowWidth = childMarginWidth;
                }

                //获取孩子控件的最高高度
                rowHeight = Math.max(rowHeight, childViewHeight);//竖的累加高度

            }

            //最后一个孩子控件
            if (i==childCount-1){

                //统级父控件的宽高
                width = Math.max(rowWidth,width);
                height += rowHeight;
            }

            //列的索引自增
            rawIndex++;

        }

        //重新设置父控件布局大小
        setMeasuredDimension(widthModel == MeasureSpec.EXACTLY ? widthSize : width + getPaddingRight() + getPaddingLeft() ,
                heightModel == MeasureSpec.EXACTLY ? heightSize : height + getPaddingTop() +getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //父控件的宽度
        int width = r - l;
        //父控件的高度
        int height = b - t;
        //获取父控件的padding值
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int finalWidth = 0;
        int finalHeight = 0;


        //列的索引
        int rowIndex =0;

        int childCount = getChildCount();

        //遍历孩子控件
        for (int i=0;i<childCount;i++){

            View childView = getChildAt(i);

            //如果孩子控件为gone
            if (childView.getVisibility()==GONE){
                continue;
            }

            //获取孩子宽高
            int childViewHeight = childView.getMeasuredHeight();
            int childViewWidth = childView.getMeasuredWidth();

            //获取孩子控件的布局属性
            MarginLayoutParams childLP = (MarginLayoutParams) childView.getLayoutParams();
            int leftMargin = childLP.leftMargin;
            int rightMargin = childLP.rightMargin;

            finalWidth = childViewWidth + leftMargin + rightMargin;//包括孩子的margin值

            //判断孩子控件累加宽是否》父控件
            if (finalWidth + paddingLeft + paddingRight > width){

                //换行
                rowIndex=0;
                //将新的一行控件leftMargin设为初始值
                paddingLeft = getPaddingLeft();

                //下一行孩子控件的top位置
                paddingTop = (int) (vertivalSpace + finalHeight);
            }
            //不过超没超，都要设置孩子控件的位置
            int left = leftMargin + paddingLeft;
            int top = childLP.topMargin + paddingTop ;
            int right = leftMargin + childView.getMeasuredWidth() + paddingLeft;
            int bottom = childLP.topMargin + childViewHeight + paddingTop;

            //设置孩子控件的位置
            childView.layout(left,top,right,bottom);

            //下一个孩子控件的left位置,如果控件是一行，那么最后一个不需要加horizontalSpace
            if (rowIndex!=childCount-1)
                finalWidth += horizontalSpace;
            paddingLeft += finalWidth;

            //下一个孩子的高度
            finalHeight = Math.max(finalHeight,childViewHeight+getPaddingTop()+leftMargin);

            rowIndex++;
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
