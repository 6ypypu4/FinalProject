package main;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager extends Employee{
	
	private Vector<FinanceOffice> offices;
	private double budget;
	private List<viewFinanceManager> observers = new ArrayList<>();
	
	public FinanceManager(int id, String name, String password, double salary) {
        super(id, name, password, salary);
        this.offices = new Vector<>();
        this.budget = 1000000; // Initial budget value - adjust as needed
    }
	
	
	 public void addFinanceOffice(FinanceOffice office) {
	        offices.add(office);
	    }
	 //aoaoaoao
	 
	   public void setSalary(Employee employee, int salary) {
	        for (FinanceOffice office : offices) {
	            if (office.getEmployees().contains(employee)) {
	                office.addEmployee(employee, salary);
	                return;
	            }
	        }
	    }
	   
	   
	   public void payday() {
	        for (FinanceOffice office : offices) {
	            System.out.println("Processing payday for office: " + office);
	            for (Employee employee : office.getEmployees()) {
	                System.out.println("Paying salary to " + employee.getName() + ": " + employee.getSalary());
	            }
	        }
	    }
	   
	   
	   public void viewBudget() {
	        System.out.println("Current budget: $" + budget);
	        System.out.println("Budget breakdown by office:");
	        for (FinanceOffice office : offices) {
	            double officeBudget = office.calculateTotalSalaries();
	            System.out.println(office + ": $" + officeBudget);
	        }
	    }

	    public void manageSalary(int employeeId, double newSalary) {
	        for (FinanceOffice office : offices) {
	            for (Employee employee : office.getEmployees()) {
	                if (employee.getId() == employeeId) {
	                    employee.setSalary(newSalary);
	                    notifyObservers();
	                    System.out.println("Salary updated successfully");
	                    return;
	                }
	            }
	        }
	        System.out.println("Employee not found");
	    }

	    @Override
	    public int getId() {
	        return super.id; 
	    }

	    public void attachObserver(viewFinanceManager observer) {
	        observers.add(observer);
	    }
	    
	    public void detachObserver(viewFinanceManager observer) {
	        observers.remove(observer);
	    }
	    
	    private void notifyObservers() {
	        for (viewFinanceManager observer : observers) {
	            observer.updateView();
	        }
	    }
}
