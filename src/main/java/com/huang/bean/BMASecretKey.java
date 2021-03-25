package com.huang.bean;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BMASecretKey {
    private Element g;
    private Element s;

    public BMASecretKey(Pairing pairing){
        g=pairing.getG1().newRandomElement();
        s=pairing.getZr().newRandomElement();
    }
}
