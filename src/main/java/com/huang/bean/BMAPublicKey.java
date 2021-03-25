package com.huang.bean;

import it.unisa.dia.gas.jpbc.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class BMAPublicKey {
    private List<Element> hs;

    public BMAPublicKey(BMASecretKey bmaSecretKey,int q){
        Element g=bmaSecretKey.getG().duplicate();
        Element s=bmaSecretKey.getS().duplicate();
        hs=new ArrayList<>();
        hs.add(g);
        for(int i=1;i<q;i++){
            Element t=s.duplicate().pow(new BigInteger(i+""));
            Element tt=g.duplicate().powZn(t.duplicate());
            hs.add(tt);
        }
    }
}
