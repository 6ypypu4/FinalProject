package Views;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.FinanceManager;
import main.MessageLoader;

public class viewFinanceManager extends viewUser {
    private FinanceManager  financeManager;
    private Scanner scanner;
    private Map<String, String> messages;
    private MessageLoader messageLoader;
    private int languageChoice;

    public viewFinanceManager(int id, int languageChoice) {
        this.scanner = new Scanner(System.in);
        this.messages = new HashMap<>();
        this.messageLoader = new MessageLoader();
        this.languageChoice = languageChoice;
        createUserFromFile("src\\Data\\users.txt");
        if (financeManager != null) {
            financeManager.attachObserver(this);
        }
    }

    @Override
    protected void loadMessages() {
        if (languageChoice == 1) {
            messageLoader.loadMessages("src\\Translations\\viewFinanceManager\\english.txt", messages);
        } else if (languageChoice == 2) {
            messageLoader.loadMessages("src\\Translations\\viewFinanceManager\\russian.txt", messages);
        } else if (languageChoice == 3) {
            messageLoader.loadMessages("src\\Translations\\viewFinanceManager\\kazakh.txt", messages);
        }
    }

    @Override
    protected void createUserFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts[0].equals("financeManager")) {
                    financeManager = new FinanceManager(Integer.parseInt(parts[1]), parts[2], parts[3], Double.parseDouble(parts[4]));
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void start() {
        loadMessages();
        boolean running = true;

        while (running) {
            System.out.println("1. " + messages.get("view_budget"));
            System.out.println("2. " + messages.get("manage_salary"));
            System.out.println("3. " + messages.get("send_message"));
            System.out.println("4. " + messages.get("view_messages")); 
            System.out.println("5. " + messages.get("view_info"));
            System.out.println("6. " + messages.get("exit"));

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                viewBudget();
            } else if (choice == 2) {
                manageSalary();
            } else if (choice == 3) {
                sendMessage();
            } else if (choice == 4) {
                viewMessages();
            } else if (choice == 5) {
                displayInfo();
            } else if (choice == 6) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private void viewBudget() {
        financeManager.viewBudget();
    }

    private void manageSalary() {
        System.out.println(messages.get("enter_employee_id"));
        int employeeId = scanner.nextInt();
        System.out.println(messages.get("enter_new_salary"));
        double newSalary = scanner.nextDouble();
        financeManager.manageSalary(employeeId, newSalary);
    }

    private void sendMessage() {
        System.out.println(messages.get("enter_employee_id"));
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(messages.get("enter_message"));
        String message = scanner.nextLine();
        financeManager.sendWorkMessage(employeeId, message);
    }

    private void viewMessages() {
        financeManager.getWorkMessages();
    }

    @Override
    protected void displayInfo() {
        System.out.println(messages.get("finance_manager_id") + ": " + financeManager.getId());
        System.out.println(messages.get("name") + ": " + financeManager.getName());
    }

    public void updateView() {
        System.out.println(messages.get("update_notification"));
        viewBudget();
    }
}