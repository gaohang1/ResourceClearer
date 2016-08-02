package com.data;

import java.util.TreeSet;

public class StyleInfo {
    
    private TreeSet<String> mDrawableList=new TreeSet<String>();
    
    public TreeSet<String> getmDrawableList() {
        return mDrawableList;
    }

    public void setmDrawableList(TreeSet<String> mDrawableList) {
        this.mDrawableList = mDrawableList;
    }

    @Override
    public String toString() {
        String result="----Style all drawable----\n\t";
        for(String s:mDrawableList){
            result+=s+" ";
        }
            
        return result;
    }
}
