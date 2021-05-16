package Validation;

import exceptions.CredentialsAreNullException;
import exceptions.InvalidCredentialsException;
import exceptions.InvalidPhoneNumberException;
import exceptions.UsernameAlreadyExistsException;
import jsonUtils.FileWriter;
import model.Guest;
import model.Organizer;
import model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class RegisterValidation
{
    public static void checkValidPassword(String password) throws InvalidCredentialsException {
        if(!checkLengthUppercaseSpace(password))
            throw new InvalidCredentialsException("Password");

    }
    public static void checkValidUsername(String username) throws InvalidCredentialsException {
        if(!checkLengthUppercaseSpace(username))
            throw new InvalidCredentialsException("Username");

    }
    public static boolean checkLengthUppercaseSpace(String str)
    {
        boolean uppercase=false;
        boolean space=false;
        if(str.length()<8)
            return false;
        for(int i=0;i<str.length();i++) {
            if(Character.isUpperCase(str.charAt(i)))
                uppercase=true;
            if(Character.isSpaceChar(str.charAt(i)))
                space=true;
        }
        if(uppercase==false || space==true)
            return false;
        return true;

    }



    public static void checkPhoneNumber(String tel) throws InvalidPhoneNumberException {
        if(tel.length()!=10)
            throw new InvalidPhoneNumberException();
        for(int i=0;i<10;i++)
        {
            if(tel.charAt(i)<'0' || tel.charAt(i)>'9')
                throw new InvalidPhoneNumberException();
        }

    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {

        for (Guest user : FileWriter.guests) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
        for (Organizer user : FileWriter.organizers) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    public static void checkCredentialsAreNotNull(User u) throws CredentialsAreNullException {
        if(u.getUsername().equals("") || u.getEmail().equals("") || u.getTel().equals("") || u.getSurname().equals("")|| u.getName().equals("")|| u.getPassword().equals(""))
            throw new CredentialsAreNullException();
    }
    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    public static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
