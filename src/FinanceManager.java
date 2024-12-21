import java.util.Vector;

public class FinanceManager extends Employee{
	
	private Vector<FinanceOffice> offices;
	private Vector<Expense> pendingExpenses;
	private double budget;
	
	public FinanceManager(int id, String name, String password, double salary) {
        super(id, name, password, salary);
        this.offices = new Vector<>();
        this.pendingExpenses = new Vector<>();
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
	   
	   
	   public void viewBudget() {
	        System.out.println("Current budget: $" + budget);
	        System.out.println("\nBudget breakdown by office:");
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
	                    System.out.println("Salary updated successfully");
	                    return;
	                }
	            }
	        }
	        System.out.println("Employee not found");
	    }

	    public void viewExpenses() {
	        System.out.println("Pending Expenses:");
	        for (Expense expense : pendingExpenses) {
	            System.out.println("ID: " + expense.getId() + 
	                             ", Amount: $" + expense.getAmount() + 
	                             ", Description: " + expense.getDescription());
	        }
	    }

	    public void approveExpense(int expenseId) {
	        for (Expense expense : pendingExpenses) {
	            if (expense.getId() == expenseId) {
	                if (budget >= expense.getAmount()) {
	                    budget -= expense.getAmount();
	                    pendingExpenses.remove(expense);
	                    System.out.println("Expense approved and processed");
	                } else {
	                    System.out.println("Insufficient budget to approve expense");
	                }
	                return;
	            }
	        }
	        System.out.println("Expense not found");
	    }

	    @Override
	    public int getId() {
	        return super.id; 
	    }
}
