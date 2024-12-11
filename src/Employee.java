public class Employee extends User{
    private double salary;

    public Employee(int id, String name, String password, double salary) {
        super(id, name, password);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
