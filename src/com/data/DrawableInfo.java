package com.data;

import java.util.TreeMap;
import java.util.TreeSet;

public class DrawableInfo {
    
    private TreeSet<String> mHDrawableList=new TreeSet<String>();
    
    private TreeMap<String,TreeSet<String>> mDrawableList=new TreeMap<String,TreeSet<String>>();

    public TreeSet<String> getmHDrawableList() {
        return mHDrawableList;
    }

    public void setmHDrawableList(TreeSet<String> mHDrawableList) {
        this.mHDrawableList = mHDrawableList;
    }
    
    public TreeMap<String, TreeSet<String>> getmDrawableList() {
        return mDrawableList;
    }

    public void setmDrawableList(TreeMap<String, TreeSet<String>> mDrawableList) {
        this.mDrawableList = mDrawableList;
    }

    @Override
    public String toString() {
        String result="----Drawable all HDrawable----\n\t";
        for(String s:mHDrawableList){
            result+=s+" ";
        }
        
        result+="\n----Drawable all Drawable----\n\t";
        for(String s:mDrawableList.keySet()){
            for(String v:mDrawableList.get(s)){
                result+=s+":"+v+" ";
            }
        }
            
        return result;
    }
}
