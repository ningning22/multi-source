package com.huang.bean;

import com.huang.util.enc.AESUtil;
import com.huang.util.enc.BytesUtil;
import com.huang.util.enc.HMacUtil;
import com.huang.util.enc.HashUtil;
import com.huang.util.tool.EncUtil;
import com.huang.util.tool.EncodingUtil;
import com.huang.util.tool.JPBCUtil;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

import java.math.BigInteger;
import java.util.List;

public class Do {
    private static final Pairing pairing=Parameters.pairing;
    private static final BMAPublicKey bmaPublicKey=Parameters.bmaPublicKey;

    String doId;
    byte[] ssi;
    int rawData;
    byte[] encData;
    String I;
    String M;
    String M1;

    public Do(String doId,int rawData,byte[] ssi){
        this.doId=doId;
        this.ssi=ssi;
        this.rawData=rawData;
    }

    public byte[] getEncData(){
        byte[] raw=BytesUtil.intToBytes(rawData);
        encData=AESUtil.encrypt(BytesUtil.keyTrim(ssi),raw,AESUtil.iv);
        return encData;
    }

    public String getI(){
        I= EncodingUtil.getIOfNumber(Parameters.prefix,rawData,Parameters.encodingLength);
        return I;
    }

    public String getM(){
        M= EncUtil.encIToM(I,Parameters.r,doId);
        return M;
    }

    private String getM1(){
        M1=EncUtil.encMToM1(doId,M,Parameters.r,Parameters.mixNum,Parameters.encodingLength);
        return M1;
    }

    //g^ssi*g^(s^h()+s^h()+...)
    private Element getSig(){
        Element sig=pairing.getZr().newOneElement();
        Element r=getR();
        List<Integer> preIndexList=EncodingUtil.getPreIndexList(I);
        Element h=getAccH(preIndexList);
        return r.duplicate().mulZn(h);
    }

    //g^ssi
    private Element getR(){
        Element ssiElement= JPBCUtil.byteToElement(ssi);
        return bmaPublicKey.getHs().get(0).duplicate().powZn(ssiElement);
    }

    //h()
    private Element getH(Integer num){
        byte[] result=HashUtil.encrypt(HashUtil.MD5,num+"");
        return pairing.getZr().newElement(new BigInteger(result));
    }

    //g^(s^h()+s^h()+...)
    private Element getAccH(List<Integer> preIndex){
        Element gs=bmaPublicKey.getHs().get(1);
        Element accH=pairing.getZr().newOneElement();
        for(Integer integer:preIndex){
            Element h=getH(integer);
            Element gsh=gs.duplicate().powZn(h);
            accH.mulZn(gsh);
        }
        return accH;
    }
}
