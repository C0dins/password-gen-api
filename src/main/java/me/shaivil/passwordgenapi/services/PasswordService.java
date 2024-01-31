package me.shaivil.passwordgenapi.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordService {

    private final String Lower = "abcdefghijklmnopqrstuvwxyz";
    private final String Upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String Digits = "0123456789";
    private final String Special = "!@#$%&*()_+-=[]|,./?><";


    public String generatePassword(int length, boolean includeUpper, boolean useNumbers,  boolean useSpecial){
        if(length <= 0){
            return null;
        }

        char[] password = new char[length];
        String charSet = Lower;
        SecureRandom secureRandom = new SecureRandom();

        if(includeUpper) charSet += Upper;
        if(useNumbers) charSet += Digits;
        if(useSpecial) charSet += Special;

        for (int i = 0; i < length; i++) {
            password[i] = charSet.toCharArray()[secureRandom.nextInt(charSet.length() - 1)];
        }
        return String.valueOf(password);
    }
}

