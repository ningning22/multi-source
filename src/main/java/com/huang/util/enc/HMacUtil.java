package com.huang.util.enc;

import com.huang.bean.ByteArray;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMacUtil {
    public static String algorithm = "HmacSHA256";
    public static byte[] mainKey = {103, 26, -66, 81, 31, 71, 19, 41, 64, -112, 86, 71, -31, 54, -113, -42, 8, -61, 28, -91, 88, -1, 113, -78, 49, 51, 14, 84, 100, 55, -83, -45};

    public static byte[] encrypt(byte[] key, byte[] data) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static byte[] encrypt(ByteArray key, ByteArray data) {
        return encrypt(key.getBytes(), data.getBytes());
    }

    public static byte[] encrypt(ByteArray key, byte[] data) {
        return encrypt(key.getBytes(), data);
    }

    public static byte[] encrypt(byte[] key, ByteArray data) {
        return encrypt(key, data.getBytes());
    }

    public static byte[] encrypt(byte[] key, String data) {
        return encrypt(key, BytesUtil.stringToBytes(data));
    }
}
