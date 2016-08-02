package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

import com.config.Config;
import com.data.LayoutInfo;

public class LayoutResolver {
    private LayoutInfo mLayoutInfo;

    public LayoutResolver(String layoutPath) {
        mLayoutInfo = new LayoutInfo();
        startLayoutResolver(layoutPath);
    }

    private void startLayoutResolver(String layoutPath) {
        File file = new File(layoutPath);
        if(!file.exists())
            return;
        for (String fileName : file.list()) {
            mLayoutInfo.getmAllLayoutSet().add(fileName.replace(".xml", ""));

            File innerFile = null;
            FileReader fileReader = null;
            BufferedReader br = null;
            try {
                innerFile = new File(layoutPath + "\\" + fileName);
                fileReader = new FileReader(innerFile);
                br = new BufferedReader(fileReader);
                String str = null;
                while ((str = br.readLine()) != null && str != "") {
                    fillLayoutInfo(fileName.replace(".xml", ""), str.trim());
                }
            } catch (Exception e) {
                System.out.println("LayoutResolver:resolve file "
                        + innerFile.getAbsolutePath() + " throw a exception "
                        + e.toString());
            } finally {
                try {
                    br.close();
                    fileReader.close();
                } catch (Exception e) {
                    System.out.println("LayoutResolver:resolve file "
                            + innerFile.getAbsolutePath()
                            + " throw a exception " + e.toString());
                }

            }

        }
    }

    private void fillLayoutInfo(String fileName, String line) {
        if (line.contains(Config.PREFIX_STRING_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_STRING_XML);
            for(String s:sl){
                if(mLayoutInfo.getmInnerStringTreeMap().containsKey(fileName)){
                    mLayoutInfo.getmInnerStringTreeMap().get(fileName).add(s);
                }else{
                    TreeSet<String> ts=new TreeSet<String>();
                    ts.add(s);
                    mLayoutInfo.getmInnerStringTreeMap().put(fileName,ts);
                }
            }
        }

        if (line.contains(Config.PREFIX_LAYOUT_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_LAYOUT_XML);
            for(String s:sl){
                if(mLayoutInfo.getmInnerLayouTreeMap().containsKey(fileName)){
                    mLayoutInfo.getmInnerLayouTreeMap().get(fileName).add(s);
                }else{
                    TreeSet<String> ts=new TreeSet<String>();
                    ts.add(s);
                    mLayoutInfo.getmInnerLayouTreeMap().put(fileName,ts);
                } 
            }
        }

        if (line.contains(Config.PREFIX_DRAWABLE_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DRAWABLE_XML);
            for(String s:sl){
                if(mLayoutInfo.getmInnerDrawableTreeMap().containsKey(fileName)){
                    mLayoutInfo.getmInnerDrawableTreeMap().get(fileName).add(s);
                }else{
                    TreeSet<String> ts=new TreeSet<String>();
                    ts.add(s);
                    mLayoutInfo.getmInnerDrawableTreeMap().put(fileName,ts);
                }
            }
        }
    }

    public LayoutInfo getmLayoutInfo() {
        return mLayoutInfo;
    }

    public void setmLayoutInfo(LayoutInfo mLayoutInfo) {
        this.mLayoutInfo = mLayoutInfo;
    }

}
