package main;

import Enums.LessonType;

public class LessonFactory implements Serializable {
    
    public boolean createLesson(Course course, String date, int lessonTypeId, Teacher teacher) {
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
        return FileHandler.appendToFile("lessons.txt", lessonInfo);
    }

    public boolean addStudent(Lesson lesson, Student student) {
        String studentInfo = lesson.getCourse().getName() + "=" + lesson.getDate() + "=" + student.getName();
        return FileHandler.appendToFile("lessons.txt", studentInfo);
    }
}
