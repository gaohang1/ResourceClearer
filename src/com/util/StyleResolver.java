package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.config.Config;
import com.data.StyleInfo;

public class StyleResolver {
    
    private StyleInfo mStyleInfo;
    
    public StyleResolver(String stylePath){
        mStyleInfo=new StyleInfo();
        startResolverStyle(stylePath);
    }
    
    private void startResolverStyle(String stylePath){
        File file =new File(stylePath);
        if(!file.exists())
            return;
        FileReader fileReader = null;
        BufferedReader br = null;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = null;
            while ((str = br.readLine()) != null && str != "") {
               fillStyleInfo(str.trim());
            }
         } catch (Exception e) {
                System.out.println("StyleResolver:resolve file "
                        + file.getAbsolutePath() + " throw a exception "
                        + e.toString());
         } finally {
                try {
                    br.close();
                    fileReader.close();
                } catch (Exception e) {
                    System.out.println("StyleResolver:resolve file "
                            + file.getAbsolutePath()
                            + " throw a exception " + e.toString());
                }
            }
    }
    
    private void fillStyleInfo(String line) {

        if (line.contains(Config.PREFIX_DRAWABLE_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DRAWABLE_XML);
            for(String s:sl){
                mStyleInfo.getmDrawableList().add(s); 
            }
        }
    }

    public StyleInfo getmStyleInfo() {
        return mStyleInfo;
    }

    public void setmStyleInfo(StyleInfo mStyleInfo) {
        this.mStyleInfo = mStyleInfo;
    }
    
}
