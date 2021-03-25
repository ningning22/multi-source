package com.huang.util;

import com.huang.util.tool.EncodingUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EncodingUtilTest {

    @Test
    public void testGetEncodingByLength(){
        List<String> result= EncodingUtil.getEncodingByLength(3);
        System.out.println(result);
    }

    @Test
    public void testGetPreEncodingByLength(){
        List<String> result=EncodingUtil.getPreEncodingByLength(3);
        System.out.println(result);
    }

    @Test
    public void testGetNumEncoding(){
        Assert.assertEquals("0010",EncodingUtil.getNumberEncoding(2,4));
        Assert.assertEquals("00010",EncodingUtil.getNumberEncoding(2,5));
    }

    @Test
    public void testGetNumberPreEncoding(){
        List<String> result = EncodingUtil.getNumberPreEncoding(2, 3);
        System.out.println(result);
    }

    @Test
    public void testGetIOfNumber(){
        String result=EncodingUtil.getIOfNumber(EncodingUtil.getPreEncodingByLength(3),2,3);
        System.out.println(result);
        result=EncodingUtil.getIOfNumber(EncodingUtil.getPreEncodingByLength(3),3,3);
        System.out.println(result);
    }
}
