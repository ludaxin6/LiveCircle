package com.deshine.huishu.app.utils;

import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
    /**
     *  使用md5方式进行加密
     *  @return
     */
    public static String md5(String content){
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
            // 16位的加密
            // return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * MD5加密.
     *
     * @param stream 待加密数据
     * @param type   加密类型，如MD5,SHA-1, SHA-224, SHA-256, SHA-384, SHA-512
     * @return 加密后的数据
     */
    public static byte[] md5(InputStream stream, String type) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(type);
            DigestInputStream dis = new DigestInputStream(stream, md5);
            byte[] buffer = new byte[4096];
            while (dis.read(buffer) > 0) ;
            return dis.getMessageDigest().digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * AES加密解密，填充方式为CBC，补码为PKCS5Padding,注意密钥和偏移量必须是16个字节.
     *
     * @param encrypt true为加密，false为解密
     * @param data    待加密解密数据
     * @param key     加密解密密钥，必须16个字节
     * @param iv      偏移量，必须16个字节
     * @return 返回加密或解密后的数据
     */
    public static byte[] aes(boolean encrypt, byte[] data, byte[] key, byte[] iv) {
        if (data != null || key != null && iv != null && key.length == 16 && iv.length == 16) {
            SecretKeySpec spec = new SecretKeySpec(key, "AES");
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                int mode = encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
                cipher.init(mode, spec, new IvParameterSpec(iv));
                return cipher.doFinal(data);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 生成RSA密钥对，密钥长度为512/1024/2048
     *
     * @param keyLength 密钥长度
     * @return 返回密钥对
     */
    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA公钥加密解密.
     *
     * @param encrypt true为加密，false为解密
     * @param data    待加密数据
     * @param key     公钥
     * @return 加密或解密后的数据
     */
    public static byte[] rsaPublicKey(boolean encrypt, byte[] data, byte[] key) {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            int mode = encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
            cipher.init(mode, publicKey);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA私钥加密解密.
     *
     * @param encrypt true为加密，false为解密
     * @param data    待加密解密数据
     * @param key     私钥
     * @return 返回加密或解密后的数据
     */
    public static byte[] rsaPrivateKey(boolean encrypt, byte[] data, byte[] key) {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = factory.generatePrivate(spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            int mode = encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
            cipher.init(mode, privateKey);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
