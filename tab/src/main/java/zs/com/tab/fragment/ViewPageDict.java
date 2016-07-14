package zs.com.tab.fragment;

import android.os.Bundle;

/**
 * Created by zhangshuqing on 16/7/11.
 */
public enum ViewPageDict {

    NEWS("资讯","news",FragmentPageA.class,null),
    BLOG("博客","blog", FragmentPageB.class,null),
    ANS("问答","ans", FragmentPageC.class,null),
    ACTIVITY("活动","activity",FragmentPageD.class,null),
    CITY("同城","city",FragmentPageE.class,null);

    private String title;
    private String tag;
    private Class clz;
    private Bundle arg;
    ViewPageDict(String title,String tag,Class clz,Bundle arg){
        this.title=title;
        this.tag=tag;
        this.clz=clz;
        this.arg=arg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Class getClz() {
        return clz;
    }

    public void setClz(Class clz) {
        this.clz = clz;
    }

    public Bundle getArg() {
        return arg;
    }

    public void setArg(Bundle arg) {
        this.arg = arg;
    }
}
