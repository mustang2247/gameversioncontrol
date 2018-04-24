package com.open.coinnews.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * 椭圆曲线数字签名算法
 */
public class ECDSA {

    public static final String NNONEwithECDSA = "NNONEwithECDSA";//       实现方:JDK/BC  签名长度:128
    public static final String RIPEMD160withECDSA = "RIPEMD160withECDSA";//   实现方:BC      签名长度:160
    public static final String SHA1withECDSA = "SHA1withECDSA";//        实现方:JDK/BC  签名长度:160
    public static final String SHA224withECDSA = "SHA224withECDSA";//      实现方:BC      签名长度:224
    public static final String SHA256withECDSA = "SHA256withECDSA";//      实现方:JDK/BC  签名长度:256
    public static final String SHA384withECDSA = "SHA384withECDSA";//      实现方:JDK/BC  签名长度:384
    public static final String SHA512withECDSA = "SHA512withECDSA";//      实现方:JDK/BC  签名长度:512

    public static void main(String[] args) {

        try {
            String src = "ecdsa security";
            KeyPair keyPair = generateKey();
            verifyECDSA((ECPublicKey) keyPair.getPublic(), signatureECDSA((ECPrivateKey) keyPair.getPrivate(), src.getBytes()), src.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 1 初始化密钥
     * @return
     * @throws Exception
     */
    public static KeyPair generateKey() throws Exception{
        //1.初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(256);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 2 执行签名
     * @param ecPrivateKey
     * @param datas
     * @return
     */
    public static String signatureECDSA(ECPrivateKey ecPrivateKey, byte[] datas){
        try {
            //2.执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance(SHA256withECDSA);
            signature.initSign(privateKey);
            signature.update(datas);

            byte[] res = signature.sign();
            System.out.println("签名："+HexBin.encode(res));

            return HexBin.encode(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 3 验证签名
     * @param ecPublicKey
     * @param res
     * @param datas
     */
    public static boolean verifyECDSA(ECPublicKey ecPublicKey, String res, byte[] datas){
        boolean result = false;
        try {
            Signature signature = Signature.getInstance(SHA256withECDSA);
            signature.initVerify(ecPublicKey);
            signature.update(datas);
            result = signature.verify(HexBin.decode(res));
            System.out.println("验证："+result);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}

