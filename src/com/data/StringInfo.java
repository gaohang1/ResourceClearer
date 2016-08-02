package com.data;

import java.util.TreeMap;

public class StringInfo {
    private TreeMap<String,Long> mZH_TWStrings=new TreeMap<String,Long>();
    private TreeMap<String,Long> mZH_CNStrings=new TreeMap<String,Long>();
    private TreeMap<String,Long> mENStrings=new TreeMap<String,Long>();

    public TreeMap<String, Long> getmZH_TWStrings() {
        return mZH_TWStrings;
    }

    public void setmZH_TWStrings(TreeMap<String, Long> mZH_TWStrings) {
        this.mZH_TWStrings = mZH_TWStrings;
    }

    public TreeMap<String, Long> getmZH_CNStrings() {
        return mZH_CNStrings;
    }

    public void setmZH_CNStrings(TreeMap<String, Long> mZH_CNStrings) {
        this.mZH_CNStrings = mZH_CNStrings;
    }

    public TreeMap<String, Long> getmENStrings() {
        return mENStrings;
    }

    public void setmENStrings(TreeMap<String, Long> mENStrings) {
        this.mENStrings = mENStrings;
    }

    @Override
    public String toString() {
        
        String result="----String----\n\tZH_CN has string:"+mZH_CNStrings.size()+"\n\tZH_TW has string:"+mZH_TWStrings.size()+"\n\tEN has string:"+mENStrings.size();
        return result;
    }
}
