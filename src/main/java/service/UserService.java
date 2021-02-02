package service;

import dao.impl.ImplUserCRUD;
import entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    public static Boolean login(User user) {
        User findUser = ImplUserCRUD.getInstance().findByLogin(user.getLogin());
        if (findUser != null) {
           if((getMd5Hash(user.getPassword())).equals(findUser.getPassword())) {
               return true;
           }
        }
        return false;
    }

    public static String getMd5Hash(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte[] digest = md.digest();
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (Byte b : bytes) {
            builder.append(String.format("%02x", b & 0xff));
        }
        return builder.toString();
    }
}
