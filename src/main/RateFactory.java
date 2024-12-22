package main;

public class RateFactory {
    public static void rateTeacher(int studentId, int teacherId, int rating) {
        String ranking = studentId + "=" + teacherId + "=" + rating;
        FileHandler.appendToFile("rating.txt", ranking);
    }
}
