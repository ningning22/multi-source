package com.huang.util;

import com.huang.bean.BMAPublicKey;
import com.huang.bean.BMASecretKey;
import com.huang.bean.Parameters;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

public class JPBCTest {
    @Test
    public void test1(){
        System.out.println(Parameters.resourcePath+"a1.properties");
        Pairing pairing= PairingFactory.getPairing(Parameters.resourcePath+"a1.properties");
        BMASecretKey bmaSecretKey=new BMASecretKey(pairing);
        BMAPublicKey bmaPublicKey=new BMAPublicKey(bmaSecretKey,30);
        System.out.println(bmaSecretKey);
        List<Element> hs=bmaPublicKey.getHs();
        for(Element e:hs){
            System.out.println(e);
        }
    }

    @Test
    public void test2(){
        Pairing pairing= PairingFactory.getPairing(Parameters.resourcePath+"a1.properties");
        BMASecretKey bmaSecretKey=new BMASecretKey(pairing);
        BMAPublicKey bmaPublicKey=new BMAPublicKey(bmaSecretKey,30);
        Element element1=bmaPublicKey.getHs().get(0).duplicate().powZn(pairing.getZr().newElement(2));
        Element element2=bmaPublicKey.getHs().get(0).duplicate().powZn(pairing.getZr().newElement(3));
        Element element3=bmaPublicKey.getHs().get(0).duplicate().powZn(pairing.getZr().newElement(6));
        Element element4=pairing.pairing(element1,element2);
        Element element5=pairing.pairing(element3,bmaPublicKey.getHs().get(0).duplicate());
        System.out.println(element4);
        System.out.println(element5);
    }

    @Test
    public void test3(){
        Pairing pairing= PairingFactory.getPairing(Parameters.resourcePath+"a1.properties");
        BMASecretKey bmaSecretKey=new BMASecretKey(pairing);
        BMAPublicKey bmaPublicKey=new BMAPublicKey(bmaSecretKey,30);
        Element element1=bmaPublicKey.getHs().get(0).duplicate()
                .powZn(bmaSecretKey.getS().duplicate().pow(new BigInteger("2")));
        Element element2=bmaPublicKey.getHs().get(1).duplicate()
                .powZn(pairing.getZr().newElement(new BigInteger("2")));
        System.out.println(element1);
        System.out.println(element2);
    }
}
