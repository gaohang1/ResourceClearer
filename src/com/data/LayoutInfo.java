package com.data;

import java.util.TreeMap;
import java.util.TreeSet;

public class LayoutInfo {
    private TreeMap<String, TreeSet<String>> mInnerLayouTreeMap=new TreeMap<String, TreeSet<String>>();
    private TreeMap<String, TreeSet<String>> mInnerStringTreeMap=new TreeMap<String, TreeSet<String>>();
    private TreeMap<String, TreeSet<String>> mInnerDrawableTreeMap=new TreeMap<String, TreeSet<String>>();
    
    private TreeSet<String> mAllLayoutSet=new TreeSet<String>();
    

    public TreeSet<String> getmAllLayoutSet() {
        return mAllLayoutSet;
    }
    public void setmAllLayoutSet(TreeSet<String> mAllLayoutSet) {
        this.mAllLayoutSet = mAllLayoutSet;
    }
    public TreeMap<String, TreeSet<String>> getmInnerLayouTreeMap() {
        return mInnerLayouTreeMap;
    }
    public void setmInnerLayouTreeMap(
            TreeMap<String, TreeSet<String>> mInnerLayouTreeMap) {
        this.mInnerLayouTreeMap = mInnerLayouTreeMap;
    }
    public TreeMap<String, TreeSet<String>> getmInnerStringTreeMap() {
        return mInnerStringTreeMap;
    }
    public void setmInnerStringTreeMap(
            TreeMap<String, TreeSet<String>> mInnerStringTreeMap) {
        this.mInnerStringTreeMap = mInnerStringTreeMap;
    }
    public TreeMap<String, TreeSet<String>> getmInnerDrawableTreeMap() {
        return mInnerDrawableTreeMap;
    }
    public void setmInnerDrawableTreeMap(
            TreeMap<String, TreeSet<String>> mInnerDrawableTreeMap) {
        this.mInnerDrawableTreeMap = mInnerDrawableTreeMap;
    }
    
    @Override
    public String toString() {
        String result="----Layout all Layout----\n\t";
        for(String s:mAllLayoutSet){
            result+=s+" ";
        }
        
        result+="\n----Layout inner layout----\n\t";
        for(String s:mInnerLayouTreeMap.keySet()){
            for(String vx:mInnerLayouTreeMap.get(s)){
                result+=s+":"+vx+" ";
            }
        }
            
        result+="\n----Layout inner string----\n\t";
        for(String s:mInnerStringTreeMap.keySet()){
            for(String vx:mInnerStringTreeMap.get(s)){
                result+=s+":"+vx+" ";
            }
        }
        
        result+="\n----Layout inner drawable----\n\t";
        for(String s:mInnerDrawableTreeMap.keySet()){
            for(String vx:mInnerDrawableTreeMap.get(s)){
                result+=s+":"+vx+" ";
            }
        }
        
        return result;
    }
}
