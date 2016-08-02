package com.data;

import java.util.TreeMap;

public class ArrayInfo {
    private TreeMap<String,Long> mZH_TWArrays=new TreeMap<String,Long>();
    private TreeMap<String,Long> mZH_CNArrays=new TreeMap<String,Long>();
    private TreeMap<String,Long> mENArrays=new TreeMap<String,Long>();

    public TreeMap<String, Long> getmZH_TWArrays() {
        return mZH_TWArrays;
    }

    public void setmZH_TWArrays(TreeMap<String, Long> mZH_TWArrays) {
        this.mZH_TWArrays = mZH_TWArrays;
    }

    public TreeMap<String, Long> getmZH_CNArrays() {
        return mZH_CNArrays;
    }

    public void setmZH_CNArrays(TreeMap<String, Long> mZH_CNArrays) {
        this.mZH_CNArrays = mZH_CNArrays;
    }

    public TreeMap<String, Long> getmENArrays() {
        return mENArrays;
    }

    public void setmENArrays(TreeMap<String, Long> mENArrays) {
        this.mENArrays = mENArrays;
    }

    @Override
    public String toString() {
        
        String result="----Array----\n\tZH_CN has array:"+mZH_CNArrays.size()+"\n\tZH_TW has array:"+mZH_TWArrays.size()+"\n\tEN has array:"+mENArrays.size();
        return result;
    }
}
