package com.demo.lyr.tool.加密解密;

import lombok.extern.slf4j.Slf4j;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * AES/CBC/PKCS5Padding加解密
 *
 * @author lyr
 * @date 2021-10-20
 */
@Slf4j
public class AESUtils {

    private static final String KEY_ALGORITHM = "AES";

    /** 默认的加密算法 */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /** 规定：偏移值和加密密钥是同一个字符串 */
    private static String key = "待定"; // todo
    private static String iv = key;


    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * key 加密密码
     * iv 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 加密数据
     */
    public static String encrypt(String content) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 密码key(超过16字节即128bit的key，需要替换jre中的local_policy.jar和US_export_policy.jar，否则报错：Illegal key size)
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("utf-8"),KEY_ALGORITHM);

            // 向量iv
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivParameterSpec);

            // 加密
            byte[] byteContent = content.getBytes("utf-8");
            byte[] result = cipher.doFinal(byteContent);

            // byte[]数组 转 Base64字符串
            return byte2Str(result);

        } catch (Exception ex) {
            System.out.println("加密失败："+ex.getMessage());
            log.info("加密失败："+ex.getMessage());
        }
        return null;
    }

    /**
     * byte[]数组 转 Base64字符串
     */
    private static String byte2Str(byte[] bytes){
        return DatatypeConverter.printBase64Binary(bytes);
    }

    /**
     * AES 解密操作
     *
     * @param content 密文
     * @return 明文
     */
    public static String decrypt(byte[] content) {

        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //密码key
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("utf-8"),KEY_ALGORITHM);

            //向量iv
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));

            //初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE,keySpec,ivParameterSpec);

            //执行操作
            byte[] result = cipher.doFinal(content);

            return new String(result,"utf-8");
        } catch (Exception ex) {
            System.out.println("解密失败："+ex.getMessage());
            log.info("解密失败："+ex.getMessage());
        }

        return null;
    }


}

