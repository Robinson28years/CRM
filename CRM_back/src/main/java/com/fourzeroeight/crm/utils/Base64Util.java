package com.fourzeroeight.crm.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Base64Util {

    public static String basecrypt(String content){
        try {
            String asB64 = Base64.getEncoder().encodeToString(content.getBytes("utf-8"));
            return asB64;
        } catch (Exception e) {
            return null;
        }
    }
    public static String basedecrypt(String content){
        try {
            byte[] asBytes = Base64.getDecoder().decode(content);
           return new String(asBytes, "utf-8");
        } catch (Exception e) {
            return null;
        }
    }

    static SecretKey key;
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        if( key == null) {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(new SecureRandom(FileUtil.KEYS.getBytes()));
            key = generator.generateKey();
            generator = null;
        }
        return key;
    }
    public static String crypt(String content){
        try {
            return getEncString(content, generateKey());
        } catch (Exception e) {
            return null;
        }
    }
    public static String decrypt(String content){
        try {
            return getDesString(content,generateKey());
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 加密String明文输入,String密文输出
     *
     * @param strMing
     * @return
     */
    private static String getEncString(String strMing,Key key) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = getEncCode(byteMing,key);
            strMi = base64en.encode(byteMi);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            base64en = null;
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }
    /**
     * 解密 以String密文输入,String明文输出
     *
     * @param strMi
     * @return
     */
    private static String getDesString(String strMi, Key key) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            byteMi = base64De.decodeBuffer(strMi);
            byteMing = getDesCode(byteMi,key);
            strMing = new String(byteMing, "UTF8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            base64De = null;
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }
    /**
     * 加密以byte[]明文输入,byte[]密文输出
     *
     * @param byteS
     * @return
     */
    private static byte[] getEncCode(byte[] byteS,Key key) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }
    /**
     * 解密以byte[]密文输入,以byte[]明文输出
     *
     * @param byteD
     * @return
     */
    private static byte[] getDesCode(byte[] byteD,Key key) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }
//    public static void main(String[] args) {
//        System.out.println(Base64Util.crypt("bindMobile=13023130171&fenjihao=107", "bbbbb", 1));
//        System.out.println(Base64Util.crypt("GT+F0fcFNGiq73/+FaX9pK9n9zqxwqz9sZ7MQdSp1BxWJXWn7EwnvniQpAOaGi0W", "bbbbb", 2));
//    }
}

