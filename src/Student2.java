import java.util.Vector;

public class Student2 {
    private String name;
    private int studentID;
    private Vector<Integer> grades; // Вектор для хранения оценок
    
    // Конструктор
    public Student2(String name, int studentID) {
        this.name = name;
        this.studentID = studentID;
        this.grades = new Vector<>();
    }
    
    // Геттеры и сеттеры
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getStudentID() {
        return studentID;
    }
    
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    
    // Метод для добавления оценки
    public void addGrade(int grade) {
        this.grades.add(grade);
    }
    
    // Метод для просмотра всех оценок
    public void viewGrades() {
        System.out.println("Оценки студента " + name + ": " + grades);
    }
    
    // Метод для вывода полной информации о студенте
    public void viewStudentInfo() {
        System.out.println("ID: " + studentID + ", Имя: " + name);
        viewGrades();
    }
}
