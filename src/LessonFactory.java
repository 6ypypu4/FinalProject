import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LessonFactory {

    private LessonType lessonType;
    private Lesson lesson;

    public LessonFactory(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public LessonFactory(Lesson lesson) {
        this.lesson = lesson;
    }

    public boolean addStudent(Lesson lesson, Student student) {
    	
        String studentInfo = lesson.getCourse().getName() + "=" + lesson.getDate() + "=" + student.getName();
        return saveToFile("src\\Data\\lessons.txt", studentInfo);
    }

    public void createLesson(Lesson lesson, Teacher teacher) {
    	
        String lessonInfo = lesson.getType() + "=" + lesson.getDate() + "=" + teacher.getName() + "=" + lesson.getCourse().getName();
        saveToFile("src\\Data\\lessons.txt", lessonInfo);
    }

    private static boolean saveToFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to " + filePath + ": " + e.getMessage());
            return false;
        }
    }
}
