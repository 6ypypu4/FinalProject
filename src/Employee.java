public class Employee extends User {
    private double salary;
    private Messenger messages = new Messenger();
    
    public Employee(int id, String name, String password, double salary) {
        super(id, name, password);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void getMail() {
		messages.readMessages(id);
	}
	
	public void sendWorkMessage(int toId, String message) {
		messages.sendMessage(toId, id, message);
	}
	
    
    
} 
