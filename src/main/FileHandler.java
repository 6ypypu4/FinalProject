package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_PATH = "src\\Data\\";
    

    public FileHandler() {
        // Constructor
    }

    // Generic method to save objects
    public static <T> void saveToFile(List<T> objects, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_PATH + fileName))) {
            oos.writeObject(objects);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    
    // Generic method to append text to file
    public static boolean appendToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_PATH + fileName, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to " + fileName + ": " + e.getMessage());
            return false;
        }
    }
    
    // Generic method to read text from file
    public static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_PATH + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from " + fileName + ": " + e.getMessage());
        }
        return lines;
    }
    
    // Generic method to overwrite file
    public static boolean overwriteFile(String fileName, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_PATH + fileName, false))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error overwriting " + fileName + ": " + e.getMessage());
            return false;
        }
    }
} 