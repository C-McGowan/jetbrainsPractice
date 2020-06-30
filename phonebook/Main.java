package phonebook;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        String pathToFile = "C:\\Users\\Callum\\Documents\\Hyperskill";
        File names = new File(pathToFile + "\\find.txt");
        File directory = new File(pathToFile + "\\directory.txt");
        List<String> peopleToFind = new ArrayList<String>();
        List<String> directoryListings = new ArrayList<String>();
        List<String> foundPeople = new ArrayList<String>();
        int count = 0;
        System.out.println("Start searching...");
        long startTime = System.currentTimeMillis();


        try (Scanner peopleScanner = new Scanner(names)) {
            while (peopleScanner.hasNext()) {
                peopleToFind.add(peopleScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile + "\\find.txt");
        }

        try (Scanner directoryScanner = new Scanner(directory)) {
            while (directoryScanner.hasNext()) {
                directoryListings.add(directoryScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile + "\\directory.txt");
        }

        for (String person : peopleToFind) {
            for (String listing : directoryListings) {
                if (listing.contains(person)) {
                    count++;
                    foundPeople.add(listing);
                    break;
                }
            }
        }


        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        long minutes = difference / 60000;
        long remainingAfterMinutes = difference % 60000;
        long seconds = remainingAfterMinutes / 1000;
        long milliseconds = remainingAfterMinutes % 1000;

        System.out.println("Found " + count + " / " + count + " entries. Time taken: " +minutes + " min. " + seconds + "sec. " + milliseconds + " ms.");
    }
}
