package com.huang.util.enc;

import com.huang.bean.ByteArray;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BytesUtil {

    public static byte[] keyTrim(byte[] key) {
        return keyTrim(key, 32);
    }

    public static byte[] keyTrim(byte[] key, int length) {
        if (key.length == length) {
            return key;
        } else if (key.length > length) {
            return Arrays.copyOfRange(key, 0, length);
        } else {
            return pad(key, length);
        }
    }

    public static byte[] pad(byte[] s) {
        return pad(s, 32);
    }

    private static byte[] pad(byte[] s, int length) {
        byte[] result = Arrays.copyOf(s, length);
        byte padding = (byte) ((length - s.length) % length);
        Arrays.fill(result, s.length, result.length, padding);
        return result;
    }

    /**
     * 去除字节数组中的填充字节,填充字节的数目就是最后一个字节的大小
     *
     * @param bytes 0x03 0x04 0x07 0x05 0x05 0x05 0x05 0x05
     * @return 0x03 0x04 0x07
     */
    public static byte[] unPad(byte[] bytes) {
        return Arrays.copyOfRange(bytes, 0, bytes[bytes.length - 1]);
    }


    /**
     * 去除字节数组尾部为0的字节
     *
     * @param bytes 字节数组 0x01 0x02 0x03 0x00 0x00
     * @return 去除尾部为0的字节数组 0x01 0x02 0x03
     */
    public static byte[] removeEndZero(byte[] bytes) {
        int nonZero = bytes.length - 1;
        while (nonZero >= 0 && bytes[nonZero] == 0) nonZero--;
        return Arrays.copyOfRange(bytes, 0, nonZero + 1);
    }

    /**
     * 将多个字节数组按顺序合并成一个新的字节数组
     *
     * @param bytes 多个字节数组
     * @return 一个字节数组
     */
    public static byte[] unionBytes(byte[]... bytes) {
        int length = 0;
        for (byte[] aByte : bytes) {
            length += aByte.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] aByte : bytes) {
            System.arraycopy(aByte, 0, result, pos, aByte.length);
            pos += aByte.length;
        }
        return result;
    }

    /**
     * 将整数转化成字节数组
     *
     * @param i 整数
     * @return 字节数组
     */
    public static byte[] intToBytes(int i) {
        return stringToBytes(String.valueOf(i));
    }

    /**
     * 将字节数组转化成整数
     *
     * @param bytes 字节数组
     * @return 整数
     */
    public static int bytesToInt(byte[] bytes) {
        return Integer.parseInt(bytesToString(bytes));
    }

    public static byte[] stringToBytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public static String bytesToString(byte[] src) {
        return new String(src, StandardCharsets.UTF_8);
    }

    /**
     * 将字节数组转化为二进制字符串
     *
     * @param bytes 字节数组
     * @return 二进制字符串
     */
    public static String bytes2BinaryString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length * 8; i++) {
            sb.append((bytes[i / 8] >> (7 - i % 8)) & 0x1);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转化成字节数组
     *
     * @param s 16进制字符串
     * @return 字节数组
     */
    public static byte[] hexStringToBytes(String s) {
        int len = s.length();
        byte[] b = new byte[(len / 2)];
        for (int i = 0; i < len; i += 2) {
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16) - 128);
        }
        return b;
    }

    /**
     * 将字节数组转化为16进制的字符串
     *
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte + 128);
            if (s.length() < 2) {
                sb.append("0").append(s);
            } else {
                sb.append(s, 0, 2);
            }
        }
        return sb.toString();
    }

    /**
     * 将多个字节数组转化成多个采用base64加密的字符串
     *
     * @param bytes 多个字节数组
     * @return base64加密的list
     */
    public static List<String> bytesToListBase64String(byte[]... bytes) {
        List<String> result = new ArrayList<>();
        for (byte[] bs : bytes) {
            result.add(new ByteArray(bs).toString());
        }
        return result;
    }
}
