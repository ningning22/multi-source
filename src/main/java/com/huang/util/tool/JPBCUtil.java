package com.huang.util.tool;

import com.huang.bean.Parameters;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class JPBCUtil {
    private static final Pairing pairing= Parameters.pairing;

    public static Element stringToElement(String s){
        byte[] bytes=s.getBytes(StandardCharsets.UTF_8);
        BigInteger bigInteger=new BigInteger(bytes);
        return pairing.getZr().newElement(bigInteger);
    }

    public static Element byteToElement(byte[] bytes){
        BigInteger bigInteger=new BigInteger(bytes);
        return pairing.getZr().newElement(bigInteger);
    }
}
