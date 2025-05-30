import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    /**
     * read the concerts from file
     * @param path given path
     * @return concerts list
     */
    public static List<Concert> readConcerts (String path) {
        List<Concert> concerts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader (new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                LocalDate date = LocalDate.parse(parts[1]);
                String venue = parts[2];
                int seats = Integer.parseInt(parts[3]);
                int booked = parts.length > 4 ? Integer.parseInt(parts[4]) : 0;
                Concert concert = new Concert(name,date,venue,seats);
                concert.setBooked(booked);
                concerts.add(concert);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read concerts file.");
        }
        return concerts;
    }

    /**
     * write the concerts to file
     * @param concerts list of concerts
     * @param path given path
     */
    public static void writeConcertsToFile(List<Concert> concerts, String path) {
        try (FileWriter fw = new FileWriter(path)) {
            for (Concert concert : concerts) {
                fw.write(concert.getName() + ",");
                fw.write(concert.getDate() + ",");
                fw.write(concert.getVenue() + ",");
                fw.write(concert.getSeats() + ",");
                fw.write(concert.getBooked() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
