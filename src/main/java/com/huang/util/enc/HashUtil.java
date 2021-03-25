package com.huang.util.enc;

import com.huang.bean.ByteArray;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String SHA_256 = "SHA-256";
    public static String SHA_512 = "SHA-512";
    public static String MD5 = "MD5";

    public static byte[] kl = {-61, -94, 8, 50, 81, -12, -103, -103, 42, 48, 66, 25, -66, 84, 114, 12, 90, 62, 61, 113,
            -115, 99, -40, -101, 66, -38, 99, 98, -55, -16, 35, 86};
    public static byte[] kr = {-2, 29, -93, 2, -16, 96, 29, -11, -121, -77, 27, -26, 70, -101, -102, 110, -6, -53, 104,
            108, 39, 82, -100, 40, -13, 7, -124, 39, -92, 31, -4, 60};


    public static byte[] k1 = {-61, -94, 8, 50, 81, -12, -103, -103, 42, 48, 66, 25, -66, 84, 114, 12, 90, 62, 61, 113,
            -115, 99, -40, -101, 66, -38, 99, 98, -55, -16, 35, 86};
    public static byte[] k2 = {-2, 29, -93, 2, -16, 96, 29, -11, -121, -77, 27, -26, 70, -101, -102, 110, -6, -53, 104,
            108, 39, 82, -100, 40, -13, 7, -124, 39, -92, 31, -4, 60};
    public static byte[] k3 = {103, 26, -66, 81, 31, 71, 19, 41, 64, -112, 86, 71, -31, 54, -113, -42, 8, -61, 28, -91,
            88, -1, 113, -78, 49, 51, 14, 84, 100, 55, -83, -45};
    public static byte[] k4 = {-66, -94, 8, 50, 81, -12, -11, -77, 42, 48, 66, 70, -66, 84, 114, 12, 90, 62, 61, 113,
            -115, 99, -40, -101, 66, -38, 99, 98, -55, -16, 35, 86};

    public static byte[] k5 = {-61, -94, 8, 50, 81, -12, -103, -103, 42, 48, 66, 25, -66, 84, 114, 12, 90, 62, 61, 113,
            -115, 99, -40, -101, 66, -38, 99, 98, -55, -16, 35, 86};
    public static byte[] k6 = {-2, 29, -93, 2, -16, 96, 29, -11, -121, -77, 27, -26, 70, -101, -102, 110, -6, -53, 104,
            108, 39, 82, -100, 40, -13, 7, -124, 39, -92, 31, -4, 60};
    public static byte[] k7 = {103, 26, -66, 81, 31, 71, 19, 41, 64, -112, 86, 71, -31, 54, -113, -42, 8, -61, 28, -91,
            88, -1, 113, -78, 49, 51, 14, 84, 100, 55, -83, -45};
    public static byte[] k8 = {-66, -94, 8, 50, 81, -12, -11, -77, 42, 48, 66, 70, -66, 84, 114, 12, 90, 62, 61, 113,
            -115, 99, -40, -101, 66, -38, 99, 98, -55, -16, 35, 86};


    public static byte[] encrypt(String instance, byte[]... msg) {
        byte[] union = BytesUtil.unionBytes(msg);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(instance);
            return messageDigest.digest(union);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return union;
    }

    public static ByteArray encryptToByteArray(String instance, byte[]... msg) {
        return new ByteArray(encrypt(instance, msg));
    }

    public static byte[] encrypt(String key, String data) {
        return encrypt(BytesUtil.stringToBytes(key), BytesUtil.stringToBytes(data));
    }

    public static ByteArray encryptToByteArray(String key, String data) {
        return new ByteArray(encrypt(BytesUtil.stringToBytes(key), BytesUtil.stringToBytes(data)));
    }

    public static byte[] encrypt(byte[]... msg) {
        return encrypt(SHA_256, msg);
    }

    public static byte[] encrypt(byte[] key, String msg) {
        return encrypt(key, BytesUtil.stringToBytes(msg));
    }

    public static ByteArray encryptToByteArray(byte[]... msg) {
        return new ByteArray(encrypt(SHA_256, msg));
    }

    public static byte[] encrypt(String str) {
        return encrypt(BytesUtil.stringToBytes(str));
    }

    public static ByteArray encryptToByteArray(String str) {
        return new ByteArray(encrypt(BytesUtil.stringToBytes(str)));
    }
}
