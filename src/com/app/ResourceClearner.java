package com.app;

import java.io.File;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.config.Config;
import com.data.ArrayInfo;
import com.data.DrawableInfo;
import com.data.LayoutInfo;
import com.data.SrcInfo;
import com.data.StringInfo;
import com.data.StyleInfo;
import com.util.ArrayResolver;
import com.util.DrawableResolver;
import com.util.LayoutResolver;
import com.util.SrcResolver;
import com.util.StringResolver;
import com.util.StyleResolver;
/**
 * 假设所有xml文件里面没有注释行即没有<!-- -->
 * 假设styles里面所有的drawable都是被用到的
 * 假设图片文件放在HDrawable，Drawable文件夹下面放图片的xml文件
 * 
 * @author YL-Z0033
 *
 */
public class ResourceClearner {
    
    private static String mRoot;
    
    public static final int DIFF_STRING=0;
    public static final int DIFF_ARRAY=1;
    
    private static SrcInfo mSrcInfo;
    private static LayoutInfo mLayoutInfo;
    private static DrawableInfo mDrawableInfo;
    private static StyleInfo mStyleInfo;
    private static StringInfo mStringInfo;
    private static ArrayInfo mArrayInfo;
    
    private static TreeSet<String> mRemoveLayout=new TreeSet<String>();
    private static TreeSet<String> mRemoveDrawable=new TreeSet<String>();
    private static TreeSet<String> mRemoveHDrawable=new TreeSet<String>();
    private static TreeMap<String,Long> mRemoveCNString=new TreeMap<String,Long>();
    private static TreeMap<String,Long> mRemoveTWString=new TreeMap<String,Long>(); 
    private static TreeMap<String,Long> mRemoveENString=new TreeMap<String,Long>();
    private static TreeMap<String,Long> mRemoveCNArray=new TreeMap<String,Long>();
    private static TreeMap<String,Long> mRemoveTWArray=new TreeMap<String,Long>(); 
    private static TreeMap<String,Long> mRemoveENArray=new TreeMap<String,Long>();
    
    public static void main(String[] args) throws Exception{
//        if (args.length == 0)
//            return;
//        mRoot = args[0];
//扫描本地工程的路径
        mRoot="E:/Android Development/Project/05_Implementation/trunk/DuPontLargeFarmers";
        if(!new File(mRoot).exists()){
            System.out.println("You give the project's root path is wrong!!!");
            return;
        }
        
        System.out.println("========= start ========");
        
        startResolver();
        
        checkLayouts();
        checkDrawables();
        checkStrings();
        checkArrays();

        System.out.println("\n\n========= end =========");
    }
    
    private static void startResolver(){
        
        SrcResolver srcResolver=new SrcResolver(mRoot+Config.DES_SRC_PATH);
        mSrcInfo=srcResolver.getmSrcInfo();
        System.out.println(mSrcInfo.toString());
        
        LayoutResolver layoutResolver=new LayoutResolver(mRoot+Config.DES_LAYOUT_PATH);
        mLayoutInfo=layoutResolver.getmLayoutInfo();
        System.out.println(mLayoutInfo.toString());
        
        DrawableResolver drawableResolver=new DrawableResolver(mRoot+Config.DES_DRAWABLE_PATH,mRoot+Config.DES_HDRAWABLE_PATH);
        mDrawableInfo=drawableResolver.getmDrawableInfo();
        System.out.println(mDrawableInfo.toString());
        
        StyleResolver styleResolver=new StyleResolver(mRoot+Config.DES_STYLE_PATH);
        mStyleInfo=styleResolver.getmStyleInfo();
        System.out.println(mStyleInfo.toString()+"\n\n");
        
        StringResolver stringResolver=new StringResolver(mRoot+Config.DES_CN_STRING_PATH,mRoot+Config.DES_TW_STRING_PATH,mRoot+Config.DES_EN_STRING_PATH);
        mStringInfo=stringResolver.getmStringInfo();
        
        ArrayResolver arrayResolver=new ArrayResolver(mRoot+Config.DES_CN_ARRAY_PATH,mRoot+Config.DES_TW_ARRAY_PATH,mRoot+Config.DES_EN_ARRAY_PATH);
        mArrayInfo=arrayResolver.getmArrayInfo();
    }
    
    private static void checkArrays() {

        System.out.println("\n\n========= checking arrays ========");
        System.out.println(mArrayInfo.toString()+"\n");
        checkDiff(DIFF_ARRAY);
        
        System.out.println("--- remove the following arrays under res\\values-zh-rCN\\arrays.xml ---");
        
        for(String s:mArrayInfo.getmZH_CNArrays().keySet()){
            if(!mSrcInfo.getmArrayList().contains(s)){
                mRemoveCNArray.put(s,mArrayInfo.getmZH_CNArrays().get(s));
            }
        }
        if(mRemoveCNArray.isEmpty()){
            System.out.println("\tYou don't need remove any array under res\\values-zh-rCN\\arrays.xml");
        }else{
            System.out.println("\tRemove array list: ");
            for(String s:mRemoveCNArray.keySet()){
                System.out.println("\t\tline: "+mRemoveCNArray.get(s)+" key: "+s);
            }
            System.out.println("\tRemove array count is "+mRemoveCNArray.size()+"\n");
        }
        
        System.out.println("--- remove the following arrays under res\\values-zh-rTW\\arrays.xml ---");
        for(String s:mArrayInfo.getmZH_TWArrays().keySet()){
            if(!mSrcInfo.getmArrayList().contains(s)){
                mRemoveTWArray.put(s,mArrayInfo.getmZH_TWArrays().get(s));
            }
        }
        if(mRemoveTWArray.isEmpty()){
            System.out.println("\tYou don't need remove any array under res\\values-zh-rTW\\arrays.xml");
        }else{
            System.out.println("\tRemove array list: ");
            for(String s:mRemoveTWArray.keySet()){
                System.out.println("\t\tline: "+mRemoveTWArray.get(s)+" key: "+s);
            }
            System.out.println("\tRemove array count is "+mRemoveTWArray.size()+"\n");
        }
        
        System.out.println("--- remove the following arrays under res\\values\\arrays.xml ---");
        for(String s:mArrayInfo.getmENArrays().keySet()){
            if(!mSrcInfo.getmArrayList().contains(s)){
                mRemoveENArray.put(s,mArrayInfo.getmENArrays().get(s));
            }
        }
        if(mRemoveENArray.isEmpty()){
            System.out.println("\tYou don't need remove any array under res\\values\\arrays.xml");
        }else{
            System.out.println("\tRemove array list: ");
            for(String s:mRemoveENArray.keySet()){
                System.out.println("\t\tline: "+mRemoveENArray.get(s)+" key: "+s);
            }
            System.out.println("\tRemove array count is "+mRemoveENArray.size()+"\n");
        }
    
    }
    
    private static void checkStrings() {
        System.out.println("\n========= checking strings ========");
        System.out.println(mStringInfo.toString()+"\n");
        checkDiff(DIFF_STRING);
        
        System.out.println("--- remove the following strings under res\\values-zh-rCN\\strings.xml ---");
        TreeSet<String> layoutInnerString=new TreeSet<String>();
        for(String s:mLayoutInfo.getmInnerStringTreeMap().keySet()){
            if(!mRemoveLayout.contains(s)){
                layoutInnerString.addAll(mLayoutInfo.getmInnerStringTreeMap().get(s));
            }
        }
        for(String s:mStringInfo.getmZH_CNStrings().keySet()){
            if(!mSrcInfo.getmStringList().contains(s)&&!layoutInnerString.contains(s)){
                mRemoveCNString.put(s,mStringInfo.getmZH_CNStrings().get(s));
            }
        }
        if(mRemoveCNString.isEmpty()){
            System.out.println("\tYou don't need remove any string under res\\values-zh-rCN\\strings.xml");
        }else{
            System.out.println("\tRemove string list: ");
            for(String s:mRemoveCNString.keySet()){
                System.out.println("\t\tline: "+mRemoveCNString.get(s)+" key: "+s);
            }
            System.out.println("\tRemove string count is "+mRemoveCNString.size()+"\n");
        }
        
        System.out.println("--- remove the following strings under res\\values-zh-rTW\\strings.xml ---");
        for(String s:mStringInfo.getmZH_TWStrings().keySet()){
            if(!mSrcInfo.getmStringList().contains(s)&&!layoutInnerString.contains(s)){
                mRemoveTWString.put(s,mStringInfo.getmZH_TWStrings().get(s));
            }
        }
        if(mRemoveTWString.isEmpty()){
            System.out.println("\tYou don't need remove any string under res\\values-zh-rTW\\strings.xml");
        }else{
            System.out.println("\tRemove string list: ");
            for(String s:mRemoveTWString.keySet()){
                System.out.println("\t\tline: "+mRemoveTWString.get(s)+" key: "+s);
            }
            System.out.println("\tRemove string count is "+mRemoveTWString.size()+"\n");
        }
        
        System.out.println("--- remove the following strings under res\\values\\strings.xml ---");
        for(String s:mStringInfo.getmENStrings().keySet()){
            if(!mSrcInfo.getmStringList().contains(s)&&!layoutInnerString.contains(s)){
                mRemoveENString.put(s,mStringInfo.getmENStrings().get(s));
            }
        }
        if(mRemoveENString.isEmpty()){
            System.out.println("\tYou don't need remove any string under res\\values\\strings.xml");
        }else{
            System.out.println("\tRemove string list: ");
            for(String s:mRemoveENString.keySet()){
                System.out.println("\t\tline: "+mRemoveENString.get(s)+" key: "+s);
            }
            System.out.println("\tRemove string count is "+mRemoveENString.size()+"\n\n");
        }
    }


    private static void checkDiff(int w) {
        if(w==DIFF_STRING){
            HashSet<String> commCNTW=new HashSet<String>(mStringInfo.getmZH_CNStrings().keySet());
            commCNTW.retainAll(mStringInfo.getmZH_TWStrings().keySet());
            HashSet<String> reCN=new HashSet<String>(mStringInfo.getmZH_CNStrings().keySet());
            HashSet<String> reTW=new HashSet<String>(mStringInfo.getmZH_TWStrings().keySet());
            reCN.removeAll(commCNTW);
            reTW.removeAll(commCNTW);
            System.out.println("--- Compare CN AND TW ---\n\tCN EXTRA STRING: ");
            for(String s:reCN){
                System.out.println("\t\tline:"+mStringInfo.getmZH_CNStrings().get(s)+" key:"+s);
            }
            System.out.println("\tTW EXTRA STRING: ");
            for(String s:reTW){
                System.out.println("\t\tline:"+mStringInfo.getmZH_TWStrings().get(s)+" key:"+s);
            }
            
            HashSet<String> commCNEN=new HashSet<String>(mStringInfo.getmZH_CNStrings().keySet());
            commCNEN.retainAll(mStringInfo.getmENStrings().keySet());
            HashSet<String> resCN=new HashSet<String>(mStringInfo.getmZH_CNStrings().keySet());
            HashSet<String> resEN=new HashSet<String>(mStringInfo.getmENStrings().keySet());
            resCN.removeAll(commCNEN);
            resEN.removeAll(commCNEN);
            System.out.println("--- Compare CN AND EN ---\n\tCN EXTRA STRING: ");
            for(String s:resCN){
                System.out.println("\t\tline:"+mStringInfo.getmZH_CNStrings().get(s)+" key:"+s);
            }
            System.out.println("\tEN EXTRA STRING: ");
            for(String s:resEN){
                System.out.println("\t\tline:"+mStringInfo.getmENStrings().get(s)+" key:"+s);
            }
        }else if(w==DIFF_ARRAY){
            HashSet<String> commCNTW=new HashSet<String>(mArrayInfo.getmZH_CNArrays().keySet());
            commCNTW.retainAll(mArrayInfo.getmZH_TWArrays().keySet());
            HashSet<String> reCN=new HashSet<String>(mArrayInfo.getmZH_CNArrays().keySet());
            HashSet<String> reTW=new HashSet<String>(mArrayInfo.getmZH_TWArrays().keySet());
            reCN.removeAll(commCNTW);
            reTW.removeAll(commCNTW);
            System.out.println("--- Compare CN AND TW ---\n\tCN EXTRA ARRAY: ");
            for(String s:reCN){
                System.out.println("\t\tline:"+mArrayInfo.getmZH_CNArrays().get(s)+" key:"+s);
            }
            System.out.println("\tTW EXTRA ARRAY: ");
            for(String s:reTW){
                System.out.println("\t\tline:"+mArrayInfo.getmZH_TWArrays().get(s)+" key:"+s);
            }
            
            HashSet<String> commCNEN=new HashSet<String>(mArrayInfo.getmZH_CNArrays().keySet());
            commCNEN.retainAll(mArrayInfo.getmENArrays().keySet());
            HashSet<String> resCN=new HashSet<String>(mArrayInfo.getmZH_CNArrays().keySet());
            HashSet<String> resEN=new HashSet<String>(mArrayInfo.getmENArrays().keySet());
            resCN.removeAll(commCNEN);
            resEN.removeAll(commCNEN);
            System.out.println("--- Compare CN AND EN ---\n\tCN EXTRA ARRAY: ");
            for(String s:resCN){
                System.out.println("\t\tline:"+mArrayInfo.getmZH_CNArrays().get(s)+" key:"+s);
            }
            System.out.println("\tEN EXTRA ARRAY: ");
            for(String s:resEN){
                System.out.println("\t\tline:"+mArrayInfo.getmENArrays().get(s)+" key:"+s);
            }
        }
       
        
    }


    private static void checkDrawables() {
        System.out.println("========= checking drawables ========");
        System.out.println("--- remove the following drawables under res\\drawable ---");
        
        TreeSet<String> layoutInnerDrawable=new TreeSet<String>();
        for(String s:mLayoutInfo.getmInnerDrawableTreeMap().keySet()){
            if(!mRemoveLayout.contains(s)){//except removed layout's inner drawable
                layoutInnerDrawable.addAll(mLayoutInfo.getmInnerDrawableTreeMap().get(s));
            }
        }
        for(String s:mDrawableInfo.getmDrawableList().keySet()){
            if((!mSrcInfo.getmDrawableList().contains(s))&&(!layoutInnerDrawable.contains(s))&&!mStyleInfo.getmDrawableList().contains(s)){
                mRemoveDrawable.add(s);
            }
        }
        if(mRemoveDrawable.isEmpty()){
            System.out.println("\tYou don't need remove any drawable under res\\drawable");
        }else{
            String s="";
            for(String l:mRemoveDrawable){
                s+=l+" ";
            }
            System.out.println("\tRemove drawable list: "+s);
            System.out.println("\tRemove drawable count is "+mRemoveDrawable.size()+"\n");
        }
        
        System.out.println("--- remove the following drawables under res\\drawable-hdpi ---");
        TreeSet<String> drawInnerDrawable=new TreeSet<String>();
        for(String s:mDrawableInfo.getmDrawableList().keySet()){
            if(!mRemoveDrawable.contains(s)){
                drawInnerDrawable.addAll(mDrawableInfo.getmDrawableList().get(s));
            }
        }
        for(String d:mDrawableInfo.getmHDrawableList()){
            if(!mSrcInfo.getmDrawableList().contains(d)&&!layoutInnerDrawable.contains(d)&&!drawInnerDrawable.contains(d)&&!mStyleInfo.getmDrawableList().contains(d)){
                mRemoveHDrawable.add(d);
            }
        }
        if(mRemoveHDrawable.isEmpty()){
            System.out.println("\tYou don't need remove any drawable under res\\drawable-hdpi");
        }else{
            String s="";
            for(String l:mRemoveHDrawable){
                s+=l+" ";
            }
            System.out.println("\tRemove HDrawable list: "+s);
            System.out.println("\tRemove HDrawable count is "+mRemoveHDrawable.size()+"\n");
        }
        
    }


    private static void checkLayouts() throws Exception {
        System.out.println("========= checking layouts ========");
        System.out.println("--- remove the following layouts under res\\layout ---");
        
        for(String l:mLayoutInfo.getmAllLayoutSet()){
            if(!mSrcInfo.getmLayOutList().contains(l)){
                mRemoveLayout.add(l);
            }
        }
        
        //multi contain
        for(int i=0;i<10;i++){
            if(!mRemoveLayout.isEmpty()){
                for(String k:mLayoutInfo.getmInnerLayouTreeMap().keySet()){
                    for(String v:mLayoutInfo.getmInnerLayouTreeMap().get(k)){
                        if((mRemoveLayout.contains(v))&&(mSrcInfo.getmLayOutList().contains(k))){
                            mSrcInfo.getmLayOutList().add(v);
                            mRemoveLayout.remove(v);
                         }
                    }
                }
            }
        }
        
        if(mRemoveLayout.isEmpty()){
            System.out.println("\tYou don't need remove any layout under res\\layout");
        }else{
            String s="";
            for(String l:mRemoveLayout){
                s+=l+" ";
            }
            System.out.println("\tRemove layout list: "+" "+s);
            System.out.println("\tRemove layout count is "+mRemoveLayout.size()+"\n");
        }
    }
}
