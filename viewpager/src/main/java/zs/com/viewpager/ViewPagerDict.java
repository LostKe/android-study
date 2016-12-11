package zs.com.viewpager;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by zhangshuqing on 16/12/11.
 */
public enum  ViewPagerDict {

    INTRO("简介","intro",FragmentList.class,getArgs("intro")),
    FEEDBACK("评价","feedback",FragmentList.class,getArgs("feedback")),
    RELATE("相关","relate",FragmentList.class,getArgs("relate")),
    OTHER("其他","other",FragmentList.class,getArgs("other")),
    AUTHOR("作者","author",FragmentList.class,getArgs("author")),;

    private String title;
    private String tag;
    private Class clz;
    private Bundle arg;

    ViewPagerDict(String title,String tag,Class clz,Bundle arg){
        this.title=title;
        this.tag=tag;
        this.clz=clz;
        this.arg=arg;
    }


    private static Bundle getArgs(String tag){
        Bundle bundle=new Bundle();
        ArrayList<String> array=new ArrayList<String>(15);
        for (int i=1;i<=15;i++){
            array.add(tag+":第"+i+"项");
        }
        bundle.putStringArrayList("value",array);
        return bundle;
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
