package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

import com.config.Config;
import com.data.DrawableInfo;

public class DrawableResolver {
    
    private DrawableInfo mDrawableInfo;
    
    public DrawableResolver(String drawPath,String hDrawPath){
        mDrawableInfo=new DrawableInfo();
        startResolverDrawable(drawPath);
        startResolverHDrawable(hDrawPath);
    }
    
    private void startResolverHDrawable(String hDrawPath){
        File file =new File(hDrawPath);
        if(!file.exists())
            return;
        for(String hDrawName:file.list()){
            hDrawName=hDrawName.toLowerCase();
            if(hDrawName.endsWith(".9.png")){
                hDrawName=hDrawName.replace(".9.png", "");
            }else if(hDrawName.endsWith(".png")){
                hDrawName=hDrawName.replace(".png", "");
            }else if(hDrawName.endsWith(".jpg")){
                hDrawName=hDrawName.replace(".jpg", "");
            }
            mDrawableInfo.getmHDrawableList().add(hDrawName);
        } 
    }
    
    private void startResolverDrawable(String drawPath){
        File file =new File(drawPath);
        if(!file.exists())
            return;
        for(String drawName:file.list()){
            File innerFile = null;
            FileReader fileReader = null;
            BufferedReader br = null;
            try {
                innerFile = new File(drawPath + "\\" + drawName);
                fileReader = new FileReader(innerFile);
                br = new BufferedReader(fileReader);
                String str = null;
                while ((str = br.readLine()) != null && str != "") {
                    fillDrawableInfo(drawName.replace(".xml", ""), str.trim());
                }
            } catch (Exception e) {
                System.out.println("DrawableResolver:resolve file "
                        + innerFile.getAbsolutePath() + " throw a exception "
                        + e.toString());
            } finally {
                try {
                    br.close();
                    fileReader.close();
                } catch (Exception e) {
                    System.out.println("DrawableResolver:resolve file "
                            + innerFile.getAbsolutePath()
                            + " throw a exception " + e.toString());
                }

            }

        }
    }
    
    private void fillDrawableInfo(String fileName, String line) {

        if (line.contains(Config.PREFIX_DRAWABLE_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DRAWABLE_XML);
            for(String s:sl){
                if(mDrawableInfo.getmDrawableList().containsKey(fileName)){
                    mDrawableInfo.getmDrawableList().get(fileName).add(s);
                }else{
                    TreeSet<String> ts=new TreeSet<String>();
                    ts.add(s);
                    mDrawableInfo.getmDrawableList().put(fileName,ts);
                } 
            }
        }
    }

    public DrawableInfo getmDrawableInfo() {
        return mDrawableInfo;
    }

    public void setmDrawableInfo(DrawableInfo mDrawableInfo) {
        this.mDrawableInfo = mDrawableInfo;
    }
    
    
}
