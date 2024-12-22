package main;

import java.util.List;

public class Admin extends Employee {
	
    private String filterCriteria;
    private LogManager logManager;

    public Admin(int id, String name, String password, double salary) {
        super(id, name, password, salary);
        logManager = new LogManager();
    }

    public void checkLogs() {

        // Read all logs
        List<String> logs = logManager.readLogs();
        System.out.println("All Logs:");
        for (String log : logs) {
            System.out.println(log);
        }

        // Example: Filter logs by a specific user ID or action
        List<String> filteredLogs = logManager.filterLogs(filterCriteria);
        System.out.println("Filtered Logs:");
        for (String log : filteredLogs) {
            System.out.println(log);
        }
    }

    // Method to change a user using UserFactory
    public boolean createUser(int userType, int userId, String name, String password) {
        return UserFactory.createUser(userType, userId, name, password, 0);
    }

    public boolean updateUser(int userType, int userId, String name){
        return UserFactory.updateUser(userType, userId, name);
    }

    public boolean deleteUser(int userId){
        return UserFactory.deleteUser(userId);
    }


    public void setFilterCriteria(String filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    public void viewAllUsers() {
        List<String> users = UserFactory.getAllUsers();
        System.out.println("All Users:");
        for (String user : users) {
            System.out.println(user);
        }
    }
}
