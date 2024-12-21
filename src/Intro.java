import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import Enums.UserType;

public class Intro {
    public int id; // id текущего пользователя 
    public String password; // Пароль текущего пользователя
    public int languageChoice; 
    private Scanner scanner = new Scanner(System.in);
    private Map<String, String> messages = new HashMap<>(); // Для поддержки разных языков
    private Vector<Auth> auths = new Vector<>(); // Все пользоваетли
    private MessageLoader m = new MessageLoader(); // Для создания messages

    public void start() {
        selectLanguage();
        loadUsers();
        UserType userType = selectUserType();
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

//        languageChoice = scanner.nextInt();
        languageChoice = Integer.parseInt(scanner.nextLine());

        if (languageChoice == 1) {
            m.loadMessages("src\\Translations\\Intro\\english.txt", messages);
        } else if (languageChoice == 2) {
            m.loadMessages("src\\Translations\\Intro\\russian.txt", messages);
        } else if (languageChoice == 3) {
            m.loadMessages("src\\Translations\\Intro\\kazakh.txt", messages);
        }
    }

    private UserType selectUserType() {
        System.out.println(messages.get("select_user_type"));
        System.out.println("1. " + messages.get("admin"));
        System.out.println("2. " + messages.get("manager"));
        System.out.println("3. " + messages.get("teacher"));
        System.out.println("4. " + messages.get("student"));
        System.out.println("5. " + messages.get("finance_manager"));

        int type = Integer.parseInt(scanner.nextLine());
        UserType userType = null;
       
        if (type == 1) {
        	userType = UserType.ADMIN;
        } else if (type == 2) {
        	userType = UserType.MANAGER;
        } else if (type == 3) {
        	userType = UserType.TEACHER;
        } else if (type == 4) {
        	userType = UserType.STUDENT;
        } else if (type == 5) {
        	userType = UserType.FINANCE_MANAGER;
        }
        return userType;
    }

    private boolean processLogin(UserType userType) {
        System.out.println(messages.get("login"));
        int loginId = Integer.parseInt(scanner.nextLine());

        System.out.println(messages.get("password"));
        password = scanner.nextLine();

        return authenticateUser(userType, loginId, password);
    }

    private void launchView(UserType userType) {
        System.out.println(messages.get("login_successful"));
        
        switch (userType) {
        case ADMIN -> {
            viewAdmin view = new viewAdmin(id, languageChoice);
            view.start();
        }
        case MANAGER -> {
            viewManager view = new viewManager(id, languageChoice);
            view.start();
        }
        case TEACHER -> {
            viewTeacher view = new viewTeacher(id, languageChoice);
            view.start();
        }
        case STUDENT -> {
            viewStudent view = new viewStudent(id, languageChoice);
            view.start();
        }
        case FINANCE_MANAGER -> {
            viewFinanceManager view = new viewFinanceManager(id, languageChoice);
            view.start();
        }
        default -> {
            viewStudent view = new viewStudent(id, languageChoice);
            view.start();
        }
    }

    }



    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Data\\authentication.txt"))) {
            String line;
            UserType userType = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 3) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        int type = Integer.parseInt(parts[1]);
                        
                        if (type == 1) {
                        	userType = UserType.ADMIN;
                        } else if (type == 2) {
                        	userType = UserType.MANAGER;
                        } else if (type == 3) {
                        	userType = UserType.TEACHER;
                        } else if (type == 4) {
                        	userType = UserType.STUDENT;
                        } else if (type == 5) {
                        	userType = UserType.FINANCE_MANAGER;
                        } else if (type == 6) {
                        	userType = UserType.DEAN;
                        }
                        
                        String password = parts[2].trim();
                        auths.add(new Auth(id, userType, password));
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                }
//                 else {
//                    System.out.println("Invalid format in line: " + line);
//                }
            }
        } catch (IOException e) {
            System.out.println("Error occured while opening <src\\\\Data\\\\authentication.txt>");
        }
    }

    private boolean authenticateUser(UserType userType, int loginId, String password) {
        for (Auth auth : auths) {
            if (auth.login(userType, loginId, password)) {
                this.id = loginId;
                return true;
            }
        }
        return false;
    }
}
