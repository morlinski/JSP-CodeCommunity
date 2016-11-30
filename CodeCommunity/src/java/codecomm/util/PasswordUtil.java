package codecomm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.*;
//import java.util.Base64; 
//if not available use tomcat Base64.
import org.apache.tomcat.util.codec.binary.Base64;

public class PasswordUtil {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for(byte b: mdArray){
            int v = b & 0xff;
            if( v < 16 ){
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }
    
    public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        //return Base64.getEncoder().encodeToString(saltBytes);
        return Base64.encodeBase64String(saltBytes);
    }
    
    public static String hashAndSaltPassword(String password) throws NoSuchAlgorithmException {
        String salt = getSalt();
        return hashPassword(password + salt);
    }
    
     public static void checkUserName(String username) throws Exception {
        if(username==null || username.trim().isEmpty()) {
            throw new Exception("Usernames cannot be Empty.");
        } else if (username.length() < 8 || username.length() > 30){
            throw new Exception("Usernames must be between 8 to 30 character longs.");
        }}
    
    public static void checkPasswordStrength(String password) throws Exception {
        if(password==null || password.trim().isEmpty()) {
            throw new Exception("Passwords cannot be Empty.");
        } else if (password.length() < 8 || password.length() > 30){
            throw new Exception("Passwords must be between 8 to 30 character longs.");
        }
        
        final Pattern pattern = Pattern.compile("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,30}$");

        Matcher matcher = pattern.matcher(password);

            boolean found = false;
            while (matcher.find()) {
                found = true;
            }
            if(!found){
                throw new Exception("Password format is incorrect.");
            }
    }
}
