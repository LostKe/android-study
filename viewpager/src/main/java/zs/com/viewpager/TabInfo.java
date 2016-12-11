package zs.com.viewpager;

import android.os.Bundle;

/**
 * Created by zhangshuqing on 16/12/11.
 */
public class TabInfo {
    private String tag;
    private Class clz;
    private Bundle arg;
    private String title;

    public TabInfo(String title,String tag, Class clz, Bundle arg) {
        this.title=title;
        this.tag = tag;
        this.clz = clz;
        this.arg = arg;
    }

    public TabInfo(){}

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
