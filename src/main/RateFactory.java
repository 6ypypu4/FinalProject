package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RateFactory {
    private static String path = "src\\Data\\rating.txt";
    public static void rateTeacher(int studentId,int teacherId, int rating ) {
        String ranking = studentId + "=" + teacherId + "=" + rating;
        saveToFile("src\\Data\\authentication.txt", ranking);
    }


    private static boolean saveToFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("\n" + data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to " + filePath + ": " + e.getMessage());
            return false;
        }
    }
}
