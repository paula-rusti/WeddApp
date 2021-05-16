package Validation;

import exceptions.InvalidCredentialsException;
import exceptions.InvalidPhoneNumberException;
import exceptions.CredentialsAreNullException;
import model.Guest;
import model.Organizer;
import model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterValidationTest {
    @Test
    void lengthUppercaseLettersSpaceTest(){
        assertTrue(RegisterValidation.checkLengthUppercaseSpace("Aabcdefghi"));
        assertFalse(RegisterValidation.checkLengthUppercaseSpace("abcdefghi"));
    }

    @Test
    void validateUsername(){
        String testString1 = "Abcd12345";//this should pass
        String testString2 = "abc 13";//this should fail

        try{
            RegisterValidation.checkValidUsername(testString1);
        }catch(InvalidCredentialsException e){
            fail();
        }

        try{
            RegisterValidation.checkValidUsername(testString2);
            fail();
        }catch(InvalidCredentialsException e){
            ;
        }
    }

    @Test
    void validatePassword(){
        String testString1 = "eeeAB13142";//this should pass
        String testString2 = "22";//this should fail

        try{
            RegisterValidation.checkValidUsername(testString1);
        }catch(InvalidCredentialsException e){
            fail();
        }

        try{
            RegisterValidation.checkValidUsername(testString2);
            fail();
        }catch(InvalidCredentialsException e){
            ;
        }
    }

    @Test
    void validatePhoneNumber(){
        String testString1 = "1234567890";//this should pass
        String testString2 = "123asdc";//this should fail

        try{
            RegisterValidation.checkPhoneNumber(testString1);
        }catch(InvalidPhoneNumberException e){
            fail();
        }

        try{
            RegisterValidation.checkPhoneNumber(testString2);
            fail();
        }catch(InvalidPhoneNumberException e){
            ;
        }
    }

    @Test
    void validateNotNullCredentials(){
        User u1 = new Guest("AnaMaria", "Anamaria", "Jmejnre", "8139013", "ljfas@aaa", "password");
        User u2 = new Organizer("AnaMaria", "", "Jmejnre", "8139013", "ljfas@aaa", "password");

        try {
            RegisterValidation.checkCredentialsAreNotNull(u1);
        }catch(CredentialsAreNullException e){
            fail();
        }

        try{
            RegisterValidation.checkCredentialsAreNotNull(u2);
            fail();
        }catch(CredentialsAreNullException e){
            ;
        }
    }

}
