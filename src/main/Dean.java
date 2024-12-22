package main;

import java.util.Vector;

public class Dean extends Employee {
	
	private Vector<Complaint> complaints;
	
	public Dean(int id, String name, String password, double salary) {
		super(id, name, password, salary);
	}
	
	public void getCompalints() {
		for (Complaint c : complaints) System.out.println(c);			
		
	}

}