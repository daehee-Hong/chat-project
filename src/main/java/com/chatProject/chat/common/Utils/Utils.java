package com.chatProject.chat.common.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static Map<String,String> makeSecurityByPw(String userPw) throws NoSuchAlgorithmException {
        Map<String,String> result = new HashMap<>();
        String hex;

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);

        // SALT 생성
        String salt = new String(Base64.getEncoder().encode(bytes));
        String rawAndSalt = userPw+salt;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // 평문+salt 암호화
        md.update(rawAndSalt.getBytes());
        hex = String.format("%064x", new BigInteger(1, md.digest()));

        result.putIfAbsent("SALT", salt);
        result.putIfAbsent("HEX", hex);
        return result;
    }
}
