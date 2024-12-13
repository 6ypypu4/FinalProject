import java.util.Scanner;

public abstract class viewEmployee extends viewUser{

    void sendMessage(int id) {
        Messenger messenger = new Messenger();

        System.out.println("id:");
        Scanner scanner = new Scanner(System.in);
        int employeeId = Integer.parseInt(scanner.nextLine());
        System.out.println("message");
        String massage = scanner.nextLine();
        messenger.sendMessage(employeeId, id, massage);
    }
}
