package com.yalogs.redisclient;

import com.yalogs.redisclient.key.RsaUtils;
import org.junit.Test;

import java.util.Base64;

public class KeyTest {


    @Test
    public void test1() throws Exception{
        String passwd = "wl110511";

        // 生成密钥对
        RsaUtils.generatePair();

        //解密
        String encrypt = RsaUtils.encrypt(passwd, RsaUtils.getPublicKey());
        System.out.println(encrypt);

        // 加密
        String decrypt = RsaUtils.decrypt(encrypt, RsaUtils.getPrivateKey());
        System.out.println(decrypt);


    }
}
