package com.huang.util.tool;

import com.huang.util.enc.HMacUtil;
import com.huang.util.enc.HashUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EncUtil {

    /**
     * 获取ri
     * @param r 共享的随机值
     * @param num 前缀编码下标i
     * @return ri
     */
    public static byte[] getRI(byte[] r,int num){
        return HMacUtil.encrypt(r,num+"");
    }

    /**
     * 获取GRI(doId)
     * @param rI 加密密钥
     * @param doId 数据源id
     * @return 最后一个字节的最后一个比特，以字符0，1返回
     */
    public static char getGRI(byte[] rI,String doId){
        byte[] enc=HMacUtil.encrypt(rI,doId);
        byte lastByte= (byte) (enc[enc.length-1]&0x01);
        if(lastByte==0)return '0';
        else return '1';
    }

    /**
     * 将I加密成M
     * @param I 前缀编码的数据
     * @param r 共享的随机值
     * @param doId 数据源id
     * @return M
     */
    public static String encIToM(String I,byte[] r,String doId){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<I.length();i++){
            char ch=I.charAt(i);
            byte[] rInNum=getRI(r,i);
            char enc=getGRI(rInNum,doId);
            if(ch==enc)sb.append("0");
            else sb.append("1");
        }
        return sb.toString();
    }

    /**
     * 对M添加混淆位
     * @param doId 数据源id
     * @param M 加密I的中间结果
     * @param r 共享的随机值
     * @param mixNum 混淆数量
     * @param length doId的长度
     * @return 混淆M后的结果
     */
    public static String encMToM1(String doId,String M,byte[] r,int mixNum,int length){
        byte[] hash= HashUtil.encrypt(r);
        String hashStr=bytesToBinaryString(hash);
        List<String> mixDoList=getMixDoList(hashStr,mixNum,length);
        if(mixDoList.contains(doId)){
            return M+"1";
        }else {
            return M+"0";
        }
    }

    /**
     * 将多个字节转化为二进制字符串
     * @param hash 字节数组
     * @return 二进制字符串
     */
    public static String bytesToBinaryString(byte[] hash){
        StringBuilder sb=new StringBuilder();
        for(byte b:hash){
            String s=Integer.toBinaryString(b);
            s="0".repeat(8-s.length())+s;
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 获取被混淆的doId的list
     * @param hashStr r的hash后的二进制字符串
     * @param mixNum 混淆数量
     * @param length doId的长度
     * @return 应该被混淆的doId的list
     */
    public static List<String> getMixDoList(String hashStr, int mixNum, int length){
        Set<String> result=new HashSet<>();
        for(int i=0;i<mixNum && (i+1)*length<=hashStr.length();i++){
            result.add(hashStr.substring(i*length,(i+1)*length));
        }
        return new ArrayList<>(result);
    }
}
