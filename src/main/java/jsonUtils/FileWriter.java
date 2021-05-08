package jsonUtils;
import Validation.RegisterValidation;
import exceptions.*;
import model.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileWriter
{
    private static final Path GuestPATH = FilePath.getPathToFile("config", "guests.json");
    private static final Path OrganizerPATH = FilePath.getPathToFile("config", "organizers.json");
    private static final Path WeddingPATH = FilePath.getPathToFile("config", "weddings.json");

    private static List<Organizer> organizers;      //the lists are edited in memory and then are written to file
    private static List<Guest> guests;
    private static List<Wedding> weddings;

    private static Map<String, User> userMap=new HashMap<>();

    public static void loadDataFromFile() throws IOException   //loads data from file in the class lists
    public static List<Organizer> getOrganizers()
    {
        return organizers;
    }

    public static List<Guest> getGuests()
    {
        return guests;
    }

    public static void loadUsersFromFile() throws IOException   //loads data from file in the class lists
    {

        if (!Files.exists(GuestPATH)) {
            FileUtils.copyURLToFile(FileWriter.class.getClassLoader().getResource("guests.json"), GuestPATH.toFile());
        }
        if (!Files.exists(OrganizerPATH)) {
            FileUtils.copyURLToFile(FileWriter.class.getClassLoader().getResource("organizers.json"), OrganizerPATH.toFile());
        }
        if (!Files.exists(WeddingPATH)) {
            FileUtils.copyURLToFile(FileWriter.class.getClassLoader().getResource("weddings.json"), WeddingPATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        guests = objectMapper.readValue(GuestPATH.toFile(), new TypeReference<List<Guest>>() {
        });
        organizers = objectMapper.readValue(OrganizerPATH.toFile(), new TypeReference<List<Organizer>>() {
        });
        weddings = objectMapper.readValue(WeddingPATH.toFile(), new TypeReference<List<Wedding>>() {
        });

        for(var temp : organizers)
        {
            userMap.put(temp.getUsername(), temp);
        }
        for(var temp : guests)
        {
            userMap.put(temp.getUsername(), temp);
        }

    }

    public static void persistUsers() {    //writes users list to file
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(GuestPATH.toFile(), guests);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(OrganizerPATH.toFile(), organizers);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static void persistWed() {    //writes weddings list to file
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(WeddingPATH.toFile(), weddings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User u)
    {
    public static void addUser(User u) throws UsernameAlreadyExistsException,CredentialsAreNullException , InvalidPhoneNumberException {
        RegisterValidation.checkCredentialsAreNotNull(u);
        RegisterValidation.checkUserDoesNotAlreadyExist(u.getUsername());
        RegisterValidation.checkPhoneNumber(u.getTel());
        RegisterValidation.checkValidUsername(u.getUsername());
        RegisterValidation.checkValidPassword(u.getPassword());
        u.setPassword(RegisterValidation.encodePassword(u.getUsername(),u.getPassword()));
        if(u instanceof Guest)
            guests.add((Guest) u);
        else if(u instanceof Organizer)
            organizers.add((Organizer) u);
        persistUsers();
    }

    public static void addWedd(Wedding wed)
    {
        weddings.add(wed);
    }

    public static List<Organizer> getOrganizers(){return organizers;}
    public static List<Guest> getGuests(){return guests;}
    public static List<Wedding> getWeddings(){return weddings;}
    public static Map<String, User> getUserMap(){return userMap;}
}
