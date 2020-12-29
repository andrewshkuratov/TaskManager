package ua.edu.sumdu.j2se.shkuratov.tasks.Controller;

import ua.edu.sumdu.j2se.shkuratov.tasks.Model.ArrayTaskList;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.TaskIO;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveController {
    private Logger logger;
    private final Scanner scanner = new Scanner(System.in);

    public void removeTask(String src) throws IOException {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        TaskIO.readText(arrayTaskList, new File(src));
        removeAtIndex(arrayTaskList);
        TaskIO.writeText(arrayTaskList, new File(src));
        logger.log(Level.INFO, "Task removed");
    }

    private void removeAtIndex(ArrayTaskList arrayTaskList) {
        System.out.println("input index of task you want to remove: ");
        int index = scanner.nextInt();
        if (arrayTaskList.remove(index)) {
            System.out.println("Removed");
        } else {
            System.out.println("Index out of range!");
        }
    }
}
