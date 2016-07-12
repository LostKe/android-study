package zs.com.tab.bean;

import android.os.Bundle;

/**
 * Created by zhangshuqing on 16/7/11.
 */
public class ViewPageInfo {

    public  String title;
    public String tag;
    public Class<?> clss;
    public Bundle args;

    public ViewPageInfo(String title, String tag, Class<?> clss, Bundle args) {
        this.title=title;
        this.tag=tag;
        this.clss=clss;
        this.args=args;

    }


}
