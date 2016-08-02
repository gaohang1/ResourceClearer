package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.config.Config;
import com.data.ArrayInfo;

public class ArrayResolver {
    public static int ZH_CN = 0;
    public static int ZH_TW = 1;
    public static int EN = 2;
    File cnFile = null;
    File twFile = null;
    File enFile = null;

    private ArrayInfo mArrayInfo;

    public ArrayResolver(String cnPath,String twPath,String enPath) {
        mArrayInfo = new ArrayInfo();

        if (new File(cnPath).exists()) {
            cnFile = new File(cnPath);
            startResolverArray(0);
        }
        if (new File(twPath).exists()) {
            twFile = new File(twPath);
            startResolverArray(1);
        }
        if (new File(enPath).exists()) {
            enFile = new File(enPath);
            startResolverArray(2);
        }
    }

    private void startResolverArray(int i) {

        FileReader fileReader = null;
        BufferedReader br = null;
        try {
            if (i == ZH_CN) {
                fileReader = new FileReader(cnFile);
            } else if (i == ZH_TW) {
                fileReader = new FileReader(twFile);
            } else if (i == EN) {
                fileReader = new FileReader(enFile);
            }

            br = new BufferedReader(fileReader);
            String str = null;
            Long n=1L;
            while ((str = br.readLine()) != null) {
                fillArrayInfo(n,str.trim(), i);
                n++;
            }
        } catch (Exception e) {
            System.out.println("ArrayResolver:resolve array.xml file throw a exception "
                    + e.toString());
        } finally {
            try {
                br.close();
                fileReader.close();
            } catch (Exception e) {
                System.out.println("ArrayResolver:resolve array.xml file throw a exception "
                        + e.toString());
            }

        }

    }

    private void fillArrayInfo(Long n,String line, int i) {
        if (i == ZH_CN && line.contains(Config.PREFIX_DEFINE_ARRAY_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DEFINE_ARRAY_XML);
            for(String s:sl){
                mArrayInfo.getmZH_CNArrays().put(s,n);
            }
        } else if (i == ZH_TW && line.contains(Config.PREFIX_DEFINE_ARRAY_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DEFINE_ARRAY_XML);
            for(String s:sl){
                mArrayInfo.getmZH_TWArrays().put(s,n);
            }
        } else if (i == EN && line.contains(Config.PREFIX_DEFINE_ARRAY_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DEFINE_ARRAY_XML);
            for(String s:sl){
                mArrayInfo.getmENArrays().put(s,n);
            }
        }
    }

    public ArrayInfo getmArrayInfo() {
        return mArrayInfo;
    }

    public void setmArrayInfo(ArrayInfo mArrayInfo) {
        this.mArrayInfo = mArrayInfo;
    }
    
}
