package com.etycx.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 武海升
 * @description
 * @date 2019/6/12 17:20
 */

public class AesUtil {

    /**
     * 密钥
     */
    private static final String KEY = "nbNDSBFBbgfckvfl";
    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * aes解密
     * @param encrypt   内容
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encrypt) {
        try {
            return aesDecrypt(encrypt, KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * aes加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) {
        try {
            return aesEncrypt(content, KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code){
        return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {

//        String aesDecrypt = AesUtil.aesDecrypt("n2w1k7k4+uT/HElZjO/6Pw==");
//        System.out.println(aesDecrypt);
//        String content = "123";
//        System.out.println("加密前：" + content);
//        System.out.println("加密密钥和解密密钥：" + KEY);
//        String encrypt = aesEncrypt(content, KEY);
//        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt("MnVqcKSyTo2Ufs0/5adjIa7uyVM8FVMMPFwaA4KCnasZQBKhXnKjfYPHar+qa9AGCGTF2wvjK4EqJ2RL5kHPaKjzSYzOaBJxEsffTYjqrbT41Sx4OaXpyzxw1G3s3sdK/U2Wo5OY5kgtJa5Kby0emKayw0i5ADAjV459sESFfbYxkjx3BduAiNZlow+BBzfjtUc3Ct1b2SHvxtrimgOnFqRC1Na/RJidmFrf0rAT0hNLbXSVoyeQZqHx1gltc5IS4LxFahYF4iDqflcVKMblosoSxHnY0bHo4n+kcdRqM9OrxBF4IPxWxLR/O8ZZwWrRvlZnSBq6Sh7OsewyWcA8mrkmyT+ARbah92PES25LuXADSNIndXJxcKrORJbUfyclYJ8liYK1AHSOmt0RNmmuHcRKzBdQ5JC6cBubNn9jo579GkqJY2+b1icMqItgxCI4UsqCmJVTqHuo2S+1uzdJ5GzaYuTQM/gZVwg3m3xBA8hJjWfcx0PQ7b6sGmHjwjyHoXDqgQDe3ewRqYaSRRnrb3rns9H19/ijEDejtiKds7wKDRl7Tci5pl/SAusStwRGl/rFSbEvHhfdo8BLTtMXAypg7WUqKVvBAWICQjY1gbZPVnQjpx33WsWbYKWTo1VdhSCK0Xx0vsVR6Px00QDIow1iyd+F3Y6EOs1QJ0Z3kp1xu5ezGTYpvUTYdiLYSKgZe9SxDo8bKr7N0Wwcl51FykXfnjKnCsLcZefEhIfGtvnNhHk5YHEtKndNNa6Y1ygRC1PbaTcx+MVcn9KIOzMxkYtB5hDN/3YIqvfmQ00MpO0XAbLh8a6aVsakSK7Wq0lFY/bKUN5kvWv+CFKiS1dsrEAtDEsIipLVUvEZOTKjuXlLd7MZQ1LC3rRqY7m7WhbeX1yl6UfY8wfPBJtl92hgGGA4HpOrG+aCiTY+Lc5ZI9kwDITGTnzB4nQ8JWSBqPr3zzSFqBRAHHtzZdnHHmUFBIufEpWmPK5O1+5j5N/DWMGp8SHwthx9mJ2/R3wmsmJnr1TRK2/m3GypLD0mApEsBCPd797Rij0D86olPChhyfi1RzcK3VvZIe/G2uKaA6cWL+QEiX5A3WaCbs4Xrdva6lvEARko+nqxHlMa/4tb+I09ZTh7BFYgB8z/TZMK8Kc4tBw55Sad7gJWNs+RWYidJLV6e0fVlOsHoRRPDE3JoLFa9pLoBtcrhgxF5NssH8ovyVKQIGsrVZ8r9tK2esIVxJsI1txwxaw18P4MKhPcIrFF354ypwrC3GXnxISHxrb5Ec/P5RJozvHmRttX0UIOQH0KyPDIdY2P4r6WoFiraxK6oWGhISjPwJCiPpMYnwslPnBNEknZoGt0+Vo51i6zvHuKe7uIoozE1McFGwHLZWfUcuME/W9GfBCTlPpSnNtpuxB1ouBVpxAyNvA1cCj4UL8jQx5cenaejFcnqvnme1VGicX7yuo2Aq5Xb2HrfS+L1T5nX04eLK3ZnqfCCd3N9w==", KEY);
        System.out.println("解密后：" + decrypt);
    }



}
