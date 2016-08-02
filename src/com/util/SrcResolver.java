package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.config.Config;
import com.data.SrcInfo;

public class SrcResolver {
    
    private SrcInfo mSrcInfo;
    
    public SrcResolver(String srcPath){
        this.mSrcInfo=new SrcInfo();
        startSrcResolver(srcPath);
    }
    
    private boolean startSrcResolver(String srcPath){
        File file=new File(srcPath);
        if(!file.exists())
            return false;
            
        if(file.isDirectory()){
            for(String filePath:file.list()){
                startSrcResolver(srcPath+"\\"+filePath);
            }
        }else{
            FileReader fileReader = null;
            BufferedReader br = null;
            try {
                fileReader=new FileReader(file);
                br=new BufferedReader(fileReader);
                String str="";
                String s="";
                while((str=br.readLine())!=null){
                    if(!str.trim().startsWith("//")){
                        s+=str.trim();
                        if(!s.endsWith(";")){
                            continue;
                        }
                        
                        fillSrcInfo(s);
                        s="";
                    }
                }
            } catch (Exception e) {
                System.out.println("SrcResolver:resolve file "+file.getAbsolutePath()+ " throw a exception "+e.toString());
            }finally{
                try {
                    br.close();
                    fileReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
        
        return true;
    }
    
    private void fillSrcInfo(String line){
        if(!line.startsWith("//")){
            if(line.contains(Config.PREFIX_STRING_SRC)){
                for(String s:CommonUtil.getValueName(line, Config.PREFIX_STRING_SRC)){
                    mSrcInfo.getmStringList().add(s);
                }
            }
            
            if(line.contains(Config.PREFIX_LAYOUT_SRC)){
                for(String s:CommonUtil.getValueName(line, Config.PREFIX_LAYOUT_SRC)){
                    mSrcInfo.getmLayOutList().add(s);
                }
            }
            
            if(line.contains(Config.PREFIX_DRAWABLE_SRC)){
                for(String s:CommonUtil.getValueName(line, Config.PREFIX_DRAWABLE_SRC)){
                    mSrcInfo.getmDrawableList().add(s);
                }
            }
            
            if(line.contains(Config.PREFIX_ARRAY_SRC)){
                for(String s:CommonUtil.getValueName(line, Config.PREFIX_ARRAY_SRC)){
                    mSrcInfo.getmArrayList().add(s);
                }
            }
        }
    }
    
    public SrcInfo getmSrcInfo() {
        return mSrcInfo;
    }

    public void setmSrcInfo(SrcInfo mSrcInfo) {
        this.mSrcInfo = mSrcInfo;
    }
    
}
