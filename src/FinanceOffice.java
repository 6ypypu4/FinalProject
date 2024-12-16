import java.util.Vector;

public class FinanceOffice {
	
	private String name;
	private Vector<Employee> employees;
	
    public FinanceOffice(String name) {
        this.name = name;
        this.employees = new Vector<>();
    }
    
    public Vector<Employee> getEmployees() {
        return employees;
    }
    
    public void addEmployee(Employee employee, int salary) {
        employee.setSalary(salary);
        employees.add(employee);
    }

    public String toString() {
        return "FinanceOffice: " + name;
    }
    
}
