package com.huang.util;

import com.huang.util.tool.EncUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EncUtilTest {
    @Test
    public void testBytesToBinaryString(){
        byte[] a=new byte[]{1,2};
        String s= EncUtil.bytesToBinaryString(a);
        System.out.println(s);
        Assert.assertEquals("0000000100000010",s);
    }

    @Test
    public void testGetMixDoList(){
        String s="0110110011";
        List<String> result=EncUtil.getMixDoList(s,3,2);
        System.out.println(result);
    }
}
