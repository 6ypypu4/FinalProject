package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;



public class MessageLoader implements Deserializable {
    public void loadMessages(String filename, Map<String, String> messages) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    messages.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading language file: " + filename);
        }
    }
}
