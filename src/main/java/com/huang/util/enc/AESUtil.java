package com.huang.util.enc;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class AESUtil {
    public static String AES_CBC_PKCS5Padding = "AES/CBC/PKCS5Padding";
    public static String AES_CFB_NOPADDING = "AES/CFB/NOPADDING";
    public static byte[] iv="0123456789012345".getBytes(StandardCharsets.UTF_8);

    static {
        fixKeyLength();
    }

    public static void fixKeyLength() {
        String errorString = "Failed manually overriding key-length permissions.";
        int newMaxKeyLength;
        try {
            if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
                Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
                Constructor con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissionCollection = con.newInstance();
                Field f = c.getDeclaredField("all_allowed");
                f.setAccessible(true);
                f.setBoolean(allPermissionCollection, true);

                c = Class.forName("javax.crypto.CryptoPermissions");
                con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissions = con.newInstance();
                f = c.getDeclaredField("perms");
                f.setAccessible(true);
                ((Map) f.get(allPermissions)).put("*", allPermissionCollection);

                c = Class.forName("javax.crypto.JceSecurityManager");
                f = c.getDeclaredField("defaultPolicy");
                f.setAccessible(true);
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                f.set(null, allPermissions);

                newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
            }
        } catch (Exception e) {
            throw new RuntimeException(errorString, e);
        }
        if (newMaxKeyLength < 256)
            throw new RuntimeException(errorString); // hack failed
    }

    public static byte[] encrypt(byte[] key, byte[] raw, byte[] iv, String instance) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        try {
            Cipher cipher = Cipher.getInstance(instance);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(raw);
        } catch (Exception e) {
            System.out.println("AES加密失败");
            System.out.println("key:" + Arrays.toString(key));
        }
        return raw;
    }

    public static byte[] encrypt(byte[] key, byte[] raw, byte[] iv) {
        return encrypt(key, raw, iv, AES_CBC_PKCS5Padding);
    }

    public static byte[] encrypt(byte[] key, String raw, byte[] iv) {
        return encrypt(key, BytesUtil.stringToBytes(raw), iv);
    }


    public static byte[] decrypt(byte[] key, byte[] raw, byte[] iv, String instance) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        try {
            Cipher cipher = Cipher.getInstance(instance);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(raw);
        } catch (Exception e) {
            System.out.println("AES解密失败");
            System.out.println("key:" + Arrays.toString(key));
        }
        return raw;
    }

    public static byte[] decrypt(byte[] key, byte[] raw, byte[] iv) {
        return decrypt(key, raw, iv, AES_CBC_PKCS5Padding);
    }
}
