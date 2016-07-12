package zs.com.tab;

import zs.com.tab.fragment.FragmentExplore;
import zs.com.tab.fragment.FragmentMe;
import zs.com.tab.fragment.FragmentNew;
import zs.com.tab.fragment.FragmentTweet;

/**
 * Created by zhangshuqing on 16/7/10.
 */
public enum MainTab {


    NEW(1,"综合",R.drawable.tab_icon_new, FragmentNew.class),
    TWEET(2,"动弹",R.drawable.tab_icon_tweet, FragmentTweet.class),
    EXPLORE(3,"发现",R.drawable.tab_icon_explore, FragmentExplore.class),
    ME(4,"我的",R.drawable.tab_icon_me, FragmentMe.class);



    private int index;
    private String name;
    private int resIcon;
    private  Class clz;
    MainTab(int index,String name,int resIcon,Class clz){
        this.index=index;
        this.name=name;
        this.resIcon=resIcon;
        this.clz=clz;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class getClz() {
        return clz;
    }

    public void setClz(Class clz) {
        this.clz = clz;
    }
}
