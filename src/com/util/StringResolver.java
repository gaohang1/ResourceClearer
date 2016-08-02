package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.config.Config;
import com.data.StringInfo;

public class StringResolver {
    public static int ZH_CN = 0;
    public static int ZH_TW = 1;
    public static int EN = 2;
    File cnFile = null;
    File twFile = null;
    File enFile = null;

    private StringInfo mStringInfo;

    public StringResolver(String cnPath,String twPath,String enPath) {
        mStringInfo = new StringInfo();

        if (new File(cnPath).exists()) {
            cnFile = new File(cnPath);
            startResolverString(0);
        }
        if (new File(twPath).exists()) {
            twFile = new File(twPath);
            startResolverString(1);
        }
        if (new File(enPath).exists()) {
            enFile = new File(enPath);
            startResolverString(2);
        }
    }

    private void startResolverString(int i) {

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
                fillStringInfo(n,str.trim(), i);
                n++;
            }
        } catch (Exception e) {
            System.out.println("StringResolver:resolve string.xml file throw a exception "
                    + e.toString());
        } finally {
            try {
                br.close();
                fileReader.close();
            } catch (Exception e) {
                System.out.println("StringResolver:resolve string.xml file throw a exception "
                        + e.toString());
            }

        }

    }

    private void fillStringInfo(Long n,String line, int i) {
        if (i == ZH_CN && line.contains(Config.PREFIX_DEFINE_STRING_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DEFINE_STRING_XML);
            for(String s:sl){
                mStringInfo.getmZH_CNStrings().put(s,n);
            }
        } else if (i == ZH_TW && line.contains(Config.PREFIX_DEFINE_STRING_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DEFINE_STRING_XML);
            for(String s:sl){
                mStringInfo.getmZH_TWStrings().put(s,n);
            }
        } else if (i == EN && line.contains(Config.PREFIX_DEFINE_STRING_XML)) {
            ArrayList<String> sl=CommonUtil.getValueName(line, Config.PREFIX_DEFINE_STRING_XML);
            for(String s:sl){
                mStringInfo.getmENStrings().put(s,n);
            }
        }
    }

    public StringInfo getmStringInfo() {
        return mStringInfo;
    }

    public void setmStringInfo(StringInfo mStringInfo) {
        this.mStringInfo = mStringInfo;
    }

}
