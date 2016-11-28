package zs.com.topbar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zs.com.topbar.R;

/**
 *
 * 自定义view  顶部控件
 * Created by zhangshuqing on 16/7/16.
 */
public class TopBar extends RelativeLayout {

    public static final int LEFT_BUTTON=1;
    public static final int Right_BUTTON=2;
    public static final int TITLE=3;


    int mLeftTextColor;
    Drawable mLeftBackground;
    String mLeftText;

    int mRightTextColor;
    Drawable mRightBackground;
    String mRightText;

    String mTitle;
    int mTitleTextColor;
    float mTitleTextSize;

    Button mLeftButton;
    Button mRightButton;
    TextView mTitleTextView;

    TopBarClickListener mListener;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor= array.getColor(R.styleable.TopBar_leftTextColor, Color.BLUE);
        mLeftBackground=array.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText=array.getString(R.styleable.TopBar_leftText);

        mRightTextColor=array.getColor(R.styleable.TopBar_rightTextColor,0);
        mRightBackground=array.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText=array.getString(R.styleable.TopBar_rightText);

        mTitle=array.getString(R.styleable.TopBar_topTitle);
        mTitleTextColor=array.getColor(R.styleable.TopBar_topTitleTextColor,0);
        mTitleTextSize= array.getDimension(R.styleable.TopBar_topTitleTextSize,10);
        array.recycle();


        //自定义view 上面的固定组件
        mLeftButton=new Button(context);
        mRightButton=new Button(context);
        mTitleTextView=new TextView(context);

        //设置样式
        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackgroundDrawable(mLeftBackground);

        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackgroundDrawable(mRightBackground);

        mTitleTextView.setText(mTitle);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(mTitleTextSize);

        //为组件元素设置相应的布局元素

        RelativeLayout.LayoutParams mLeftParams=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        addView(mLeftButton,mLeftParams);


        RelativeLayout.LayoutParams mRightParams=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        addView(mRightButton,mRightParams);

        RelativeLayout.LayoutParams mTitleParams=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        addView(mTitleTextView,mTitleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               mListener.leftButtionClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightButtonClick();
            }
        });






    }




    public interface TopBarClickListener{
        void leftButtionClick();
        void rightButtonClick();
    }


    /**
     * 基类实现listenter   提供基类的注册接口
     * @param listener
     */
    public void setOnTopBarClickListener(TopBarClickListener listener){
        this.mListener=listener;
    }

    /**
     * 控制 组件的显示
     * @param id
     * @param flag
     */
    public  void setVisable(int id,boolean flag){
        int vi=flag?View.VISIBLE:View.INVISIBLE;
       switch (id){
           case LEFT_BUTTON:
               mLeftButton.setVisibility(vi);
               break;
           case Right_BUTTON:
               mRightButton.setVisibility(vi);
               break;
           case TITLE:
               mTitleTextView.setVisibility(vi);
               break;
       }
    }


}
