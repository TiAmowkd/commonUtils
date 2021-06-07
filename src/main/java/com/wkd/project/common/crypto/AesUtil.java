package com.wkd.project.common.crypto;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author wkd
 * @version 1.0.0
 * @className AesUtil.java
 * @description aes加密方法
 * @createTime 2021-06-07 10:44
 */
public class AesUtil {

    /**
     * 长度必须是16
     */
    private static final String KEY = "ubang.2015server";
    /**
     * 长度必须是16
     */
    private static final String IV = "5ryzq9pmqpm8eyka";

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @return
     */
    public static String encrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
            byte[] byteContent = data.getBytes("utf-8");
            byte[] result = cipher.doFinal(byteContent);
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception ex) {
        }
        return "";
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @return
     */
    public static String decrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
            byte[] byteContent = Base64.getDecoder().decode(data);
            byte[] result = cipher.doFinal(byteContent);
            return Base64.getEncoder().encode(result).toString();
        } catch (Exception e) {
        }
        return "";
    }
}
