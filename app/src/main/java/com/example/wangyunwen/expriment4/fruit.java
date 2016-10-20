package com.example.wangyunwen.expriment4;

/**
 * Created by wangyunwen on 16/10/19.
 */
public class fruit {
    String name;
    String src;

    public fruit(String n, String s) {
        name = n;
        src  = s;
    }
    public String getName() {
        return name;
    }
    public int  getSrc() {
        R.drawable drawables=new R.drawable();
        //默认的id
        int resId=0x7f02000b;
        try {
            java.lang.reflect.Field field=R.drawable.class.getField(src);
            resId=(Integer)field.get(drawables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }
}
