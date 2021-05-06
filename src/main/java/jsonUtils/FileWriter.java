package jsonUtils;
import model.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
            e.printStackTrace();
        }
    }

    public static void addUser(User u)
    {
        if(u instanceof Guest)
            guests.add((Guest) u);
        else if(u instanceof Organizer)
            organizers.add((Organizer) u);
    }

}
