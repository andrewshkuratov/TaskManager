package ua.edu.sumdu.j2se.shkuratov.tasks.View;

import ua.edu.sumdu.j2se.shkuratov.tasks.Controller.ViewController;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainView {
    private Logger logger;
    private final ViewController viewController = new ViewController();
    Scanner scanner = new Scanner(System.in);

    public void launch() throws IOException {
//        logger.log(Level.INFO, "Program launched");
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
        int value = scanner.nextInt();
        System.out.println(value);
//        logger.log(Level.INFO, "User option" + value);
        if (value == 6) {
            System.out.println("Thank you for using this app!");
//            logger.log(Level.INFO, "Program finished");
        } else {
            viewController.choosenOption(value);
            printMenu();
        }
    }
}
