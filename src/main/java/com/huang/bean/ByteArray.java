package com.huang.bean;

import com.huang.util.enc.BytesUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class ByteArray implements Serializable {
    byte[] bytes;
    public static Base64.Encoder encoder = Base64.getEncoder();
    public static Base64.Decoder decoder = Base64.getDecoder();

    public static ByteArray fromNormalStr(String str) {
        return new ByteArray(BytesUtil.stringToBytes(str));
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public ByteArray(String base64EncString) {
        this.bytes = decoder.decode(base64EncString);
    }

    public ByteArray(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }

    public ByteArray() {
        this.bytes = new byte[]{0};
    }

    public ByteArray xorNormalStr(String str) {
        return xor(BytesUtil.stringToBytes(str));
    }

    public ByteArray xor(String byteArrayStr) {
        return xor(new ByteArray(byteArrayStr));
    }

    public ByteArray xor(ByteArray byteArray) {
        return xor(byteArray.getBytes());
    }

    public ByteArray xor(byte[] bytes) {
        byte[] shortBytes = this.getBytes();
        byte[] longBytes = bytes;

        if (shortBytes.length > longBytes.length) {
            byte[] t = shortBytes;
            shortBytes = longBytes;
            longBytes = t;
        }

        byte[] result = Arrays.copyOf(longBytes, longBytes.length);
        for (int i = 0; i < shortBytes.length; i++) {
            result[i] = (byte) (result[i] ^ shortBytes[i]);
        }

        return new ByteArray(BytesUtil.removeEndZero(result));
    }

    public String toNormalString() {
        return BytesUtil.bytesToString(bytes);
    }

    public static byte[][] byteArray2Bytes(List<ByteArray> byteArrays) {
        byte[][] bytes = new byte[byteArrays.size()][];
        for (int i = 0; i < bytes.length; i++) {
            ByteArray ba = byteArrays.get(i);
            bytes[i] = Arrays.copyOf(ba.getBytes(), ba.getBytes().length);
        }
        return bytes;
    }

    public boolean isNull() {
        return bytes == null || bytes.length == 0 || (bytes.length == 1 && bytes[0] == 0);
    }

    @Override
    public String toString() {
        return encoder.encodeToString(bytes);
    }
}
