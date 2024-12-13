import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogManager {

    private final String logFilePath = "src\\Data\\logs.txt";

    public LogManager() {}

    // Method to read logs from the file
    public List<String> readLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading logs: " + e.getMessage());
        }
        return logs;
    }

    // Method to filter logs by criteria (e.g., by date, id, action, or object)
    public List<String> filterLogs(String criteria) {
        List<String> logs = readLogs();
        List<String> filteredLogs = new ArrayList<>();

        for (String log : logs) {
            if (log.contains(criteria)) {
                filteredLogs.add(log);
            }
        }
        return filteredLogs;
    }
}
