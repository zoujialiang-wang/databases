//package com.example.demo.commen;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.crypto.Cipher;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.KeyStore;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateFactory;
//
///**
// * @Author JiaLiang
// * @Date 2020/12/7 下午7:17
// * @Version 1.0
// */
//public class SslUtils {
//    public static void main(String[] args) throws Exception {
//        String aaa = "测试数据";
//        System.out.println("原始数据为:" + aaa);
//        String encrypt = publicEncrypt(aaa);
//        System.out.println("公钥加密后数据是:" + encrypt);
//        String decode = privateDecode(encrypt);
//        System.out.println("私钥解密后数据是:" + decode);
//
//        String asdasd = privateEncrypt("asdasd");
//        System.out.println(asdasd);
//        String s = publicDecode(asdasd);
//        System.out.println(s);
//    }
//
//    /**
//     * 通过公钥文件进行加密数据
//     *
//     * @return 加密后经过base64处理的字符串
//     */
//    public static String publicEncrypt(String source) throws Exception {
//        InputStream fis = null;
//        try {
//            fis = new FileInputStream("/home/zou18846936743/test/src/main/resources/a.crt");
//            CertificateFactory cf = CertificateFactory.getInstance("x509");
//            Certificate cerCert = cf.generateCertificate(fis);
//            PublicKey pubKey = cerCert.getPublicKey();
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
//            byte[] sbt = source.getBytes();
//            byte[] epByte = cipher.doFinal(sbt);
//            BASE64Encoder encoder = new BASE64Encoder();
//            String epStr = encoder.encode(epByte);
//            return epStr;
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 通过公钥文件进行解密数据
//     *
//     * @param source
//     * @return
//     * @throws Exception
//     */
//    public static String publicDecode(String source) throws Exception {
//        BASE64Decoder b64d = new BASE64Decoder();
//        byte[] keyByte = b64d.decodeBuffer(source);
//        InputStream fis = null;
//        try {
//            fis = new FileInputStream("/home/zou18846936743/test/src/main/resources/a.crt");
//            CertificateFactory cf = CertificateFactory.getInstance("x509");
//            Certificate cerCert = cf.generateCertificate(fis);
//            PublicKey pubKey = cerCert.getPublicKey();
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE, pubKey);
//            byte[] epByte = cipher.doFinal(keyByte);
//            return new String(epByte, "UTF-8");
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 通过私钥文件进行解密数据
//     *
//     * @return 解密后的明文字符串
//     */
//    public static String privateDecode(String source) throws Exception {
//        BASE64Decoder b64d = new BASE64Decoder();
//        byte[] keyByte = b64d.decodeBuffer(source);
//        InputStream fis = null;
//        try {
//            fis = new FileInputStream("/wymtest.keystore");
//            KeyStore keyStore = KeyStore.getInstance("JKS");
//            char[] pscs = "123456".toCharArray();
//            keyStore.load(fis, pscs);
//            PrivateKey priKey = (PrivateKey) (keyStore.getKey("test11", pscs));
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE, priKey);
//            byte[] epByte = cipher.doFinal(keyByte);
//            return new String(epByte, "UTF-8");
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 通过私钥文件进行加密数据
//     *
//     * @return 解密后的明文字符串
//     */
//    public static String privateEncrypt(String source) {
//        InputStream fis = null;
//        try {
//            fis = new FileInputStream("/wymtest.keystore");
//            KeyStore keyStore = KeyStore.getInstance("JKS");
//            char[] pscs = "123456".toCharArray();
//            keyStore.load(fis, pscs);
//            PrivateKey priKey = (PrivateKey) (keyStore.getKey("test11", pscs));
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.ENCRYPT_MODE, priKey);
//            byte[] epByte = cipher.doFinal(source.getBytes());
//            BASE64Encoder encoder = new BASE64Encoder();
//            String epStr = encoder.encode(epByte);
//            return epStr;
//        } catch (FileNotFoundException e) {
//            System.out.println("文件未找到");
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//}