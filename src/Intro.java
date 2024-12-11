import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Intro {
    public int id;
    public String password;
    public int languageChoice;
    private Scanner scanner = new Scanner(System.in);
    private Map<String, String> messages = new HashMap<>();
    private Vector<Auth> auths = new Vector<>();
    private MessageLoader m = new MessageLoader();// Для хранения информации о пользователях

    public void start() {
        selectLanguage();
        loadUsers("src\\Data\\authentication.txt");
        int userType = selectUserType();
        if (processLogin(userType)) {
            launchView(userType);
        } else {
            System.out.println(messages.get("authentication_failed"));
        }
    }

    private void selectLanguage() {
        System.out.println("Welcome! Please select your language:");
        System.out.println("1. English");
        System.out.println("2. Русский");
        System.out.println("3. Қазақша");

        languageChoice = scanner.nextInt();
        scanner.nextLine();

        if (languageChoice == 1) {
            m.loadMessages("src\\Translations\\Intro\\english.txt", messages);
        } else if (languageChoice == 2) {
            m.loadMessages("src\\Translations\\Intro\\russian.txt", messages);
        } else if (languageChoice == 3) {
            m.loadMessages("src\\Translations\\Intro\\kazakh.txt", messages);
        }
    }

    private int selectUserType() {
        System.out.println(messages.get("select_user_type"));
        System.out.println("1. " + messages.get("admin"));
        System.out.println("2. " + messages.get("manager"));
        System.out.println("3. " + messages.get("teacher"));
        System.out.println("4. " + messages.get("student"));
        System.out.println("5. " + messages.get("finance_manager"));

        int userType = scanner.nextInt();
        scanner.nextLine();
        return userType;
    }

    private boolean processLogin(int userType) {
        System.out.println(messages.get("login"));
        int loginId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(messages.get("password"));
        password = scanner.nextLine();

        return authenticateUser(userType, loginId, password);
    }

    private void launchView(int userType) {
        System.out.println(messages.get("login_successful"));
        if (userType == 1) {
            viewAdmin view = new viewAdmin(id, languageChoice);
            view.start();
        } else if (userType == 2) {
            viewManager view = new viewManager(id, languageChoice);
            view.start();
        } else if (userType == 3) {
            viewTeacher view = new viewTeacher(id, languageChoice);
            view.start();
        } else if (userType == 4) {
            viewStudent view = new viewStudent(id, languageChoice);
            view.start();
        } else if (userType == 5) {
            viewFinanceManager view = new viewFinanceManager(id, languageChoice);
            view.start();
        }
    }



    private void loadUsers(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 3) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        int userType = Integer.parseInt(parts[1].trim());
                        String password = parts[2].trim();
                        auths.add(new Auth(id, userType, password));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users file: " + filename);
        }
    }

    private boolean authenticateUser(int userType, int loginId, String password) {
        for (Auth auth : auths) {
            if (auth.login(userType, loginId, password)) {
                this.id = loginId;
                return true;
            }
        }
        return false;
    }
}
