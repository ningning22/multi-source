package com.huang.util.tool;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EncodingUtil {
    /**
     * 获取长度为length的所有二进制编码
     * 例如，长度为3，结果如下
     * [000, 001, 010, 011, 100, 101, 110, 111]
     * @param length 二进制编码长度
     * @return 长度为length的所有二进制编码
     */
    public static List<String> getEncodingByLength(int length){
        List<String> result=new ArrayList<>();
        if(length>0){
            List<String> resultBefore=getEncodingByLength(length-1);
            for (String s : resultBefore) {
                result.add("0" + s);
            }
            for (String s : resultBefore) {
                result.add("1" + s);
            }
        }else{
            result.add("");
        }
        return result;
    }

    /**
     * 获取长度为length的所有二进制前缀编码
     * 例如，长度为3，结果如下
     * [000, 001, 010, 011, 100, 101, 110, 111, 00*, 01*, 10*, 11*, 0**, 1**, ***]
     * @param length 二进制编码长度
     * @return 长度为length的所有二进制前缀编码
     */
    public static List<String> getPreEncodingByLength(int length){
        List<String> result=new ArrayList<>();
        for(int i=length;i>=0;i--){
            List<String> item=getEncodingByLength(i);
            String suffix= "*".repeat(Math.max(0, length-i));
            for(String s:item)result.add(s+suffix);
        }
        return result;
    }

    /**
     * 获取整数的二进制编码，长度固定为length
     * 例如，num=2，length=4，结果如下
     * 0010
     * @param num 整数
     * @param length 编码长度
     * @return 二进制编码
     */
    public static String getNumberEncoding(int num,int length){
        String result=Integer.toBinaryString(num);
        result="0".repeat(Math.max(0,length-result.length()))+result;
        return result;
    }

    /**
     * 获取整数的所有前缀编码，长度固定为length
     * 例如，num=2，length=3，结果如下
     * 010，01*，0**，***
     * @param num 整数
     * @param length 编码长度
     * @return 二进制编码
     */
    public static List<String> getNumberPreEncoding(int num,int length){
        List<String> result=new ArrayList<>();
        String encoding=getNumberEncoding(num,length);
        for(int i=0;i<=length;i++){
            String suffix= "*".repeat(Math.max(0, i));
            result.add(encoding.substring(0,encoding.length()-i)+suffix);
        }
        return result;
    }

    /**
     * 获取长度为length的前缀编码数量
     * @param length 编码长度
     * @return 前缀编码数量
     */
    public static int getNumOfPreEncoding(int length){
        return (int) (Math.pow(2,length+1)-1);
    }

    /**
     * 获取整数num在前缀顺序为prefix的I的二进制编码
     * @param prefix 所有前缀值
     * @param num 整数
     * @param length 编码长度
     * @return I的二进制编码
     */
    public static String getIOfNumber(List<String> prefix,int num,int length){
        StringBuilder sb=new StringBuilder();
        List<String> encoding = getNumberPreEncoding(num, length);
        for(String s:prefix){
            if(encoding.contains(s))sb.append("1");
            else sb.append("0");
        }
        return sb.toString();
    }

    /**
     * 获取I中为1的下标的list
     * @param I 前缀编码
     * @return 下标集合
     */
    public static List<Integer> getPreIndexList(String I){
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<I.length();i++){
            if(I.charAt(i)=='1')result.add(i);
        }
        return result;
    }

    /**
     * 获取在前缀编码序列中某一前缀的下标
     * @param prefix 前嘴编码集合
     * @param pre 前缀
     * @return 下标
     */
    public static int getPreIndex(List<String> prefix,String pre){
        for(int i=0;i<prefix.size();i++){
            if(prefix.get(i).equals(pre))return i;
        }
        return -1;
    }
}
