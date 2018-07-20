package backend.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.model.User;

/**
 * Created by dogaro on 11/07/2016.
 */
/**
 * @author Dogar Octavian
 * {@link User} entries password encrypting class
 */
public class PasswordEncrypter {
	
    private static Logger LOG = LoggerFactory.getLogger(PasswordEncrypter.class);
    public static String generateHashedPassword(String password, String salt){
	
        String hashedPassword = "";
        byte[] initialBytes;

        try {
            initialBytes = (password + salt).getBytes(PropertyManager.INSTANCE.getProperty("passwordEncoding"));
            final MessageDigest algorithm = MessageDigest.getInstance(PropertyManager.INSTANCE.getProperty
                    ("passwordHashingAlgorithm"));
            algorithm.reset();
            algorithm.update(initialBytes);
            final byte[] hashedBytes = algorithm.digest();
            hashedPassword = new String(hashedBytes);
        }catch (UnsupportedEncodingException e){
        	LOG.trace("PasswordEncryption failed: unsupported encoding",e);
        	e.printStackTrace();
        }catch (NoSuchAlgorithmException nsae){
        	LOG.trace("hashing algorithm not supported",nsae);
        	nsae.printStackTrace();
        }
        return hashedPassword;

    }
}
