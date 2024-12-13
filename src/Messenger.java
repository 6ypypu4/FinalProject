import java.io.*;

public class Messenger {
    private static final String MESSAGES_FILE = "src\\Data\\messages.txt";

    public void sendMessage(int idTo, int idFrom, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MESSAGES_FILE, true))) {
            writer.write(idTo + "=" + idFrom + "=" + message);
            writer.newLine();
            System.out.println("Message sent from ID " + idFrom + " to ID " + idTo);
        } catch (IOException e) {
            System.out.println("Error writing message to file: " + e.getMessage());
        }
    }

    public void readMessages(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(MESSAGES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 3) {
                    int idTo = Integer.parseInt(parts[0]);
                    int idFrom = Integer.parseInt(parts[1]);
                    String message = parts[2];
                    if(id == idTo){
                        System.out.println("You have a message from user with ID " + idFrom + ": " + message);
                    }
                } else {
                    System.out.println("Invalid message format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading messages from file: " + e.getMessage());
        }
    }
}
