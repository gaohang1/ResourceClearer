package com.data;

import java.util.TreeSet;

public class SrcInfo {
    
    private TreeSet<String> mLayOutList=new TreeSet<String>();
    private TreeSet<String> mDrawableList=new TreeSet<String>();
    private TreeSet<String> mStringList=new TreeSet<String>();
    private TreeSet<String> mArrayList=new TreeSet<String>();
    
    public TreeSet<String> getmLayOutList() {
        return mLayOutList;
    }
    public void setmLayOutList(TreeSet<String> mLayOutList) {
        this.mLayOutList = mLayOutList;
    }
    public TreeSet<String> getmDrawableList() {
        return mDrawableList;
    }
    public void setmDrawableList(TreeSet<String> mDrawableList) {
        this.mDrawableList = mDrawableList;
    }
    public TreeSet<String> getmStringList() {
        return mStringList;
    }
    public void setmStringList(TreeSet<String> mStringList) {
        this.mStringList = mStringList;
    }
    
    public TreeSet<String> getmArrayList() {
        return mArrayList;
    }
    public void setmArrayList(TreeSet<String> mArrayList) {
        this.mArrayList = mArrayList;
    }
    @Override
    public String toString() {
        String result="----src layout----\n\t";
        for(String s:mLayOutList){
            result+=s+" ";
        }
        
        result+="\n----src drawable----\n\t";
        for(String s:mDrawableList){
            result+=s+" ";
        }
        
        result+="\n----src string----\n\t";
        for(String s:mStringList){
            result+=s+" ";
        }
        
        result+="\n----src array----\n\t";
        for(String s:mArrayList){
            result+=s+" ";
        }
        
        return result;
    }
    
}
