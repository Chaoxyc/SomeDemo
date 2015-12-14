package com.xyc.circlepercentage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * <b>创建时间</b> 2015/12/11<br>
 *
 * @author xyc <br>
 */
public class CirclePercentageView extends View {

    private final String TAG = "CirclePercentageView";
    private RectF oval;
    private RectF defaultOval;
    private int mProgress = 0;
    private int mMax = 100;
    private int mDefaultProgress = 0;
    private int mDuration = 1000;

    int width;
    int height;

    private Paint mOuterCirclePaint;

    private int outerCircleColor;

    private int outerCircleRadius = 100;

    private int outerCircleBarWidth;

    private Paint mInnerCirclePaint;

    private int innerCircleColor;

    private int innerCircleRadius;

    private int innerCircleBarWidth;

    //================================================================
    private int offset;

    public CirclePercentageView(Context context) {
        this(context, null);
    }

    public CirclePercentageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CirclePercentageView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CirclePercentageView_outerCircleColor:
                    outerCircleColor = a.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CirclePercentageView_outerCircleBarWidth:
                    outerCircleBarWidth = (int) a.getDimension(attr, 50);
                    break;
                case R.styleable.CirclePercentageView_outerCircleRadius:
                    outerCircleRadius = (int) a.getDimension(attr, 100);
                    break;
                case R.styleable.CirclePercentageView_innerCircleColor:
                    innerCircleColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CirclePercentageView_innerCircleBarWidth:
                    innerCircleBarWidth = (int) a.getDimension(attr, 50);
                    break;
                case R.styleable.CirclePercentageView_innerCircleRadius:
                    innerCircleRadius = (int) a.getDimension(attr, 80);
                    break;
            }
        }
        a.recycle();
        mOuterCirclePaint = new Paint();
        mOuterCirclePaint.setAntiAlias(true);
        mOuterCirclePaint.setColor(outerCircleColor);
        mOuterCirclePaint.setStyle(Paint.Style.STROKE);
        mOuterCirclePaint.setStrokeWidth(outerCircleBarWidth);

        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setAntiAlias(true);
        mInnerCirclePaint.setColor(innerCircleColor);
        mInnerCirclePaint.setStyle(Paint.Style.STROKE);
        mInnerCirclePaint.setStrokeWidth(innerCircleBarWidth);
        mInnerCirclePaint.setAlpha(125);

        offset = outerCircleBarWidth*2/3 ;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CirclePercentageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY ) {
            width = widthSize;
        } else {
            width = 2 * (outerCircleRadius + outerCircleBarWidth);
        }
        if (heightMode == MeasureSpec.EXACTLY ) {
            height = heightSize;
        } else {
            height = width;
        }
        width = height = width > height ? width : height;

        setMeasuredDimension(width, width);

        defaultOval = new RectF(offset, offset, width - offset, height - offset);

        oval = new RectF(offset, offset, width - offset, height - offset);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        oval :指定圆/弧的外轮廓矩形区域。
//        startAngle: 圆弧起始角度，单位为度。
//        sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度。
//        useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。
//        paint: 绘制圆弧的画板属性，如颜色，是否填充等。
//        oval = new RectF(new Rect(50, 50, width, height));
//        defaultOval = new RectF(new Rect(50, 50, width, height));
        canvas.drawArc(defaultOval, -90, 360, false, mOuterCirclePaint);

        canvas.drawArc(oval, -90, 360 * mDefaultProgress/100, false, mInnerCirclePaint);

        mDefaultProgress += 2;

        if (mDefaultProgress > mProgress*100/mMax) {
            // do nothing
        } else {
            invalidate();
        }
    }

    public void setmMax(int mMax) {
        this.mMax = mMax;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
    }

//    public void setmDuration(int mDuration) {
//        this.mDuration = mDuration;
//    }

    public void startTurn() {
        mDefaultProgress = 0;
        invalidate();
    }

}
