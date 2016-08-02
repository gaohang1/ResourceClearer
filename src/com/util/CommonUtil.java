package com.util;

import java.util.ArrayList;

public class CommonUtil {
    
    public static ArrayList<String> getValueName(String line, String prefix) {
        ArrayList<String> result=new ArrayList<String>();
        int idx = line.indexOf(prefix);
        while(idx>=0){
            line=line.substring(idx+prefix.length());  
            int i=0;
            while((line.charAt(i)=='_')||(line.charAt(i)>='0'&&line.charAt(i)<='9')||(line.charAt(i)>='a'&&line.charAt(i)<='z')){
                i++;
            }
            String s=line.substring(0,i);
            result.add(s);
            
            line=line.substring(i);
            idx=line.indexOf(prefix);
        }
        return result;
    }
    
}
