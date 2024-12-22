package main;

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
    
    public double calculateTotalSalaries() {
        double total = 0;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }

	@Override
	public String toString() {
		return "FinanceOffice [name=" + name + 
				", employees=" + employees + "]";
	}
    
    
}
