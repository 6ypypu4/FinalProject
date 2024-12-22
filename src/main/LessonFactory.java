package main;

import Enums.LessonType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LessonFactory implements Serializable{

    public LessonFactory() {
    }

    public boolean createLesson(Course course, String date, int lessonTypeId, Teacher teacher) {
        // Convert lessonTypeId to corresponding LessonType
        LessonType type;
        
        if (lessonTypeId == 1) {
            type = LessonType.LECTURE;
        } else if (lessonTypeId == 2) {
            type = LessonType.PRACTICE;
        } else if (lessonTypeId == 3) {
            type = LessonType.LAB;
        } else {
            return false;
        }
        
        String lessonInfo = type + "=" + date + "=" + teacher.getName() + "=" + course.getName();
        return saveToFile("src\\Data\\lessons.txt", lessonInfo);
    }

    public boolean addStudent(Lesson lesson, Student student) {
        String studentInfo = lesson.getCourse().getName() + "=" + lesson.getDate() + "=" + student.getName();
        return saveToFile("src\\Data\\lessons.txt", studentInfo);
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
