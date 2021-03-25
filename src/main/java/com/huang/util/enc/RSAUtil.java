package com.huang.util.enc;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

    public static KeyPair getRSAKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(1024, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            return keyPairGen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取私钥
    public static RSAPrivateKey getRSAPrivateKey(byte[] key) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取公钥
    public static RSAPublicKey getRSAPublicKey(byte[] key) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    //公钥加密
    public static byte[] encrypt(RSAPublicKey rsaPublicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //公钥解密
    public static byte[] decrypt(RSAPublicKey rsaPublicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //私钥加密
    public static byte[] encrypt(RSAPrivateKey rsaPrivateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //私钥解密
    public static byte[] decrypt(RSAPrivateKey rsaPrivateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
