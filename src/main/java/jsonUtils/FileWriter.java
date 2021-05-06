package jsonUtils;
import exceptions.CouldNotWriteUsersException;
import exceptions.CredentialsAreNullException;
import exceptions.InvalidPhoneNumberException;
import exceptions.UsernameAlreadyExistsException;
import model.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class FileWriter
{
    private static final Path GuestPATH = FilePath.getPathToFile("config", "guests.json");
    private static final Path OrganizerPATH = FilePath.getPathToFile("config", "organizers.json");

    private static List<Organizer> organizers;      //the lists are edited in memory and then are written to file
    private static List<Guest> guests;

    public static void loadUsersFromFile() throws IOException   //loads data from file in the class lists
    {

        if (!Files.exists(GuestPATH)) {
            FileUtils.copyURLToFile(FileWriter.class.getClassLoader().getResource("guests.json"), GuestPATH.toFile());
        }
        if (!Files.exists(OrganizerPATH)) {
            FileUtils.copyURLToFile(FileWriter.class.getClassLoader().getResource("organizers.json"), OrganizerPATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        guests = objectMapper.readValue(GuestPATH.toFile(), new TypeReference<List<Guest>>() {
        });
        organizers = objectMapper.readValue(OrganizerPATH.toFile(), new TypeReference<List<Organizer>>() {
        });

    }

    public static void persistUsers() {    //writes the data to file
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(GuestPATH.toFile(), guests);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(OrganizerPATH.toFile(), organizers);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static void addUser(User u) throws UsernameAlreadyExistsException,CredentialsAreNullException , InvalidPhoneNumberException {
        checkCredentialsAreNotNull(u);
        checkUserDoesNotAlreadyExist(u.getUsername());
        checkPhoneNumber(u.getTel());
        u.setPassword(encodePassword(u.getUsername(),u.getPassword()));
        if(u instanceof Guest)
            guests.add((Guest) u);
        else if(u instanceof Organizer)
            organizers.add((Organizer) u);
        persistUsers();
    }

    private static void checkPhoneNumber(String tel) throws InvalidPhoneNumberException {
        if(tel.length()!=10)
            throw new InvalidPhoneNumberException();
        for(int i=0;i<10;i++)
        {
            if(tel.charAt(i)<'0' || tel.charAt(i)>'9')
                throw new InvalidPhoneNumberException();
        }

    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (Guest user : guests) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
        for (Organizer user : organizers) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    private static void checkCredentialsAreNotNull(User u) throws CredentialsAreNullException {
        if(u.getUsername()=="" || u.getEmail()=="" || u.getTel()=="" || u.getSurname()==""|| u.getName()==""|| u.getPassword()=="")
            throw new CredentialsAreNullException();
    }
    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

}
