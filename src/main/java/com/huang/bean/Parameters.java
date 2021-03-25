package com.huang.bean;

import com.huang.util.tool.EncodingUtil;
import it.unisa.dia.gas.jpbc.Pairing;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Parameters {
    public static String resourcePath=Parameters.class.getResource("/").getPath();

    public static int encodingLength=4;
    public static List<String> prefix= EncodingUtil.getPreEncodingByLength(encodingLength);
    public static byte[] r="01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8);
    public static int mixNum=2;

    public static Pairing pairing;
    public static BMASecretKey bmaSecretKey;
    public static BMAPublicKey bmaPublicKey;

//    static{
//        pairing= PairingFactory.getPairing("a1.properties");
//        bmaSecretKey=new BMASecretKey(pairing);
//        bmaPublicKey=new BMAPublicKey(bmaSecretKey,32);
//    }
}
