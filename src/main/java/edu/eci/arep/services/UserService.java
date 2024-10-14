package edu.eci.arep.services;

import edu.eci.arep.models.User;
import edu.eci.arep.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Service for user management.
 * @author Andres Arias
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to check if the user exists for logging in
     * @param body user credentials
     * @return true if the user exists, false otherwise
     */
    public Boolean login(User body) throws NoSuchAlgorithmException {
        if(userRepository.existsByEmail(body.getEmail()) && generatePasswordHash(body.getPassword()).equals(userRepository.findByEmail(body.getEmail()).getPassword())){
            return true;
        }
        return false;
    }

    /**
     * Method to register a new user
     * @param body user credentials
     * @return true if the user was registered, false otherwise
     */
    public Boolean register(User body) throws NoSuchAlgorithmException {
        if(!userRepository.existsByEmail(body.getEmail())){
            body.setPassword(generatePasswordHash(body.getPassword()));
            userRepository.save(body);
            return true;
        }
        return false;
    }

    /**
     * Method to generate a password hash
     * @param password
     * @return password hash
     * @throws NoSuchAlgorithmException if the algorithm is not supported
     */
    public static String generatePasswordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    /***
     * Method to convert a byte array to a hexadecimal string
     * @param hash byte array to convert
     * @return hexadecimal string
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
