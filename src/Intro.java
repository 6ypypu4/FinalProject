import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Intro {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, String> messages = new HashMap<>();
    private Vector<User> users = new Vector<>(); // Для хранения информации о пользователях

    public void start() {
        System.out.println("Welcome! Please select your language:");
        System.out.println("1. English");
        System.out.println("2. Русский");
        System.out.println("3. Қазақша");

        int languageChoice = scanner.nextInt();
        scanner.nextLine();

        // reading translates
        if (languageChoice == 1) {
            loadMessages("src\\Translations\\Intro\\english.txt");
        } else if (languageChoice == 2) {
            loadMessages("src\\Translations\\Intro\\russian.txt");
        } else if (languageChoice == 3) {
            loadMessages("src\\Translations\\Intro\\kazakh.txt");
        }

        // Загружаем пользователей из файла
        loadUsers("users.txt");

        System.out.println(messages.get("select_user_type"));
        // choose role
        System.out.println("1. " + messages.get("admin"));
        System.out.println("2. " + messages.get("manager"));
        System.out.println("3. " + messages.get("teacher"));
        System.out.println("4. " + messages.get("student"));
        System.out.println("5. " + messages.get("finance_manager"));

        int userType = scanner.nextInt();
        scanner.nextLine();

        // Запрос логина и пароля
        System.out.println(messages.get("login"));
        int loginId = scanner.nextInt();
        scanner.nextLine();  // Пропускаем лишний символ новой строки

        System.out.println(messages.get("password"));
        String password = scanner.nextLine();

        // Проверка логина и пароля
        if (authenticateUser(loginId, password)) {
            System.out.println("Login successful!");

            // Показываем описание выбранной роли и запускаем соответствующую вьюшку
            if (userType == 1) {
                viewAdmin view = new viewAdmin();
                view.start();
            } else if (userType == 2) {
                viewManager view = new viewManager();
                view.start();
            } else if (userType == 3) {
                viewTeacher view = new viewTeacher();
                view.start();
            } else if (userType == 4) {
                viewStudent view = new viewStudent();
                view.start();
            } else if (userType == 5) {
                viewFinanceManager view = new viewFinanceManager();
                view.start();
            }
        } else {
            System.out.println(messages.get("authentication_failed"));
        }
    }

    private void loadMessages(String filename) {
        // Загружаем переводы из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Разделяем строку на ключ и значение по знаку '='
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    messages.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading language file: " + filename);
        }
    }

    private void loadUsers(String filename) {
        // Загружаем данные пользователей из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Разделяем строку на логин, пароль и id
                String[] parts = line.split("=", 3);  // Логин=пароль=id
                if (parts.length == 3) {
                    String login = parts[0];
                    String password = parts[1];
                    int id = Integer.parseInt(parts[2]);
                    User user = new User(id, login, password); // Создаем объект User
                    users.add(user); // Добавляем в вектор
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users file: " + filename);
        }
    }

    private boolean authenticateUser(int loginId, String password) {
        // Проверка логина и пароля
        for (User user : users) {
            if (user.login(loginId, password)) {
                return true;
            }
        }
        return false;
    }
}
