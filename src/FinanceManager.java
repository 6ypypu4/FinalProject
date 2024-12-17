import java.util.Vector;

public class FinanceManager extends Employee{
	
	private Vector<FinanceOffice> offices;
	
	public FinanceManager(int id, String name, String password, double salary) {
        super(id, name, password, salary);
        this.offices = new Vector<>();
    }
	
	
	 public void addFinanceOffice(FinanceOffice office) {
	        offices.add(office);
	    }
	 //aoaoaoao
	 
	   public void setSalary(Employee employee, int salary) {
	        for (FinanceOffice office : offices) {
	            if (office.getEmployees().contains(employee)) {
	                office.addEmployee(employee, salary);
	                System.out.println("Salary set for " + employee.getName() + ": " + salary);
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
	   
	   
}
