package ua.edu.sumdu.j2se.shkuratov.tasks.View;

import ua.edu.sumdu.j2se.shkuratov.tasks.Controller.ViewController;

import java.io.IOException;
import java.util.Scanner;

public class MainView {
    private final ViewController viewController = new ViewController();
    Scanner scanner = new Scanner(System.in);

    public void launch() throws IOException {
        printMenu();
    }

    private void printMenu() throws IOException {
        System.out.println("Choose an action:");
        System.out.println("1. Print tasks");
        System.out.println("2. Add Task");
        System.out.println("3. Change task");
        System.out.println("4. Delete task");
        System.out.println("5. Print calendar");
        System.out.println("6. Exit");
        int value = Integer.parseInt(scanner.nextLine());
        System.out.println(value);
        if (value == 6) {
            System.out.println("Thank you for using this app!");
        } else {
            viewController.chosenOption(value);
            printMenu();
        }
    }
}
