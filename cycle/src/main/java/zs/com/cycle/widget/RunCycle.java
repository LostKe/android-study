package zs.com.cycle.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import zs.com.cycle.R;

/**
 * 运动 渐变的圆弧
 * Created by zhangshuqing on 16/10/22.
 */
public class RunCycle extends View {

    private int textColor;
    private int initColor;
    private int runColor;
    private int circleWidth;
    private int speed;
    private int textSize;


    private Paint mPaint;
    private Paint textPaint;

    private boolean isNext=false;

    private int mProgress;

    public RunCycle(Context context) {
        this(context,null);
    }

    public RunCycle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    private int center;
    private int radius;
    private RectF oval;

    private Rect mBound;//文本绘制的范围
    private String currentText="0";

    public RunCycle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RunCycle);
        textColor=array.getColor(R.styleable.RunCycle_textColor,Color.GREEN);
        initColor = array.getColor(R.styleable.RunCycle_initColor, Color.BLUE);
        runColor = array.getColor(R.styleable.RunCycle_runColor, Color.RED);
        circleWidth = array.getInt(R.styleable.RunCycle_circleWidth, 100);
        speed = array.getInt(R.styleable.RunCycle_speed, 20);
        textSize=array.getInt(R.styleable.RunCycle_textSize,12);
        array.recycle();
        mPaint = new Paint();
        textPaint=new Paint();


        new Thread(){
            @Override
            public void run() {
               drawArc();
            }
        }.start();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureLenth(widthMeasureSpec), measureLenth(heightMeasureSpec));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        center = getWidth() / 2; //圆心的坐标
        radius = center - circleWidth / 2;//半径=画笔到圆心的距离
        //圆弧的矩形区域
        if(oval==null){
            oval=new RectF(center-radius,center-radius,center+radius,center+radius);
        }



        mPaint.setStrokeWidth(circleWidth);
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置空心

        if(isNext){
            mPaint.setColor(runColor);
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(initColor);
            canvas.drawArc(oval,-90,mProgress,false,mPaint);//画扇形
        }else{
            mPaint.setColor(initColor);
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(runColor);
            canvas.drawArc(oval,-90,mProgress,false,mPaint);
        }
        if(mBound==null){
            mBound=new Rect();
            textPaint.getTextBounds(currentText,0,currentText.length(),mBound);
        }
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        canvas.drawText(currentText,center-mBound.width()/2,center+mBound.height()/2,textPaint);//在圆心处写文字


    }


    private int measureLenth(int measureSpec) {
        int result = 150;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            default:
                result = Math.min(size, result);
                break;
        }
        return result;
    }

    private void drawArc(){
        while (true){
            mProgress++;
            if(mProgress==360){
                mProgress=0;
                isNext=!isNext;

            }
            currentText=(mProgress*100/360)+"%";
            postInvalidate();

            try {
                Thread.sleep(100/speed);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
