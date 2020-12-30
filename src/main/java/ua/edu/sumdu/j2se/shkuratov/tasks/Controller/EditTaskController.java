package ua.edu.sumdu.j2se.shkuratov.tasks.Controller;

import ua.edu.sumdu.j2se.shkuratov.tasks.Model.ArrayTaskList;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.Task;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.TaskIO;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditTaskController {
    private Logger logger;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ArrayTaskList arrayTaskList = new ArrayTaskList();

    public void chooseTask(String src) throws IOException {
        TaskIO.readText(arrayTaskList, new File(src));
        System.out.println("Choose index of task you want to edit");
        int index = scanner.nextInt();
        if (index > arrayTaskList.size() || index <= 0) {
            System.out.println(index);
            System.out.println(arrayTaskList.size());
            System.out.println("Index out of range");
            return;
        }

        Task task = arrayTaskList.getTask(index - 1);
        if (task.isRepeated()) {
            repeatMenuOptions(task);
        } else {
            nonRepeatMenuOptions(task);
        }
        arrayTaskList.changeTask(index - 1, task);
        TaskIO.writeText(arrayTaskList, new File(src));
//        logger.log(Level.INFO, "Task edited");
    }

    private void repeatMenuOptions(Task task) throws IOException {
        System.out.println("Choose an action:");
        System.out.println("1. Edit title");
        System.out.println("2. Edit start time");
        System.out.println("3. Edit end time");
        System.out.println("4. Make active/nonactive");
        System.out.println("5. Remove time interval and end time");
        System.out.println("6. Cancel editing");

        int value = scanner.nextInt();
        System.out.println(value);
        chosenRepeatOption(value, task);
    }

    private void nonRepeatMenuOptions(Task task)
            throws IOException {
        System.out.println("Choose an action:");
        System.out.println("1. Edit title");
        System.out.println("2. Edit time");
        System.out.println("3. Make active/nonactive");
        System.out.println("4. Set time interval and end time");
        System.out.println("5. Cancel editing");

        int value = scanner.nextInt();
        System.out.println(value);
        chosenNonRepeatOption(value, task);
    }

    private void chosenRepeatOption(int i, Task task)
            throws IOException {
        switch (i) {
            case 1:
                String ttl = changeTitle();
                task.setTitle(ttl);
                break;
            case 2:
                LocalDateTime end = changeTime("Enter new start time");
                task.setTime(end,
                        task.getEndTime(),
                        task.getRepeatInterval());
                break;
            case 3:
                LocalDateTime start = changeTime("Enter new start time");
                task.setTime(task.getStartTime(),
                        start,
                        task.getRepeatInterval());
                break;
            case 4:
                task.setActive(!task.isActive());
                break;
            case 5:
                Task t = new Task(task.getTitle(), task.getStartTime());
                task = t;
                break;
            default:
                break;
        }
    }

    private void chosenNonRepeatOption(int i, Task task)
            throws IOException {
        switch (i) {
            case 1:
                String ttl = changeTitle();
                task.setTitle(ttl);
                break;
            case 2:
                task.setTime(changeTime("Enter new time"));
                break;
            case 3:
                task.setActive(!task.isActive());
                break;
            case 4:
                LocalDateTime end = changeTime("Enter end time");
                task.setTime(task.getStartTime(),
                        end,
                        setInterval());
                break;
            default:
                break;
        }
    }

    private String changeTitle() {
        System.out.print("Enter new title : ");
        return scanner.next();
    }

    private LocalDateTime changeTime(String message) {
        System.out.print(message);
        String date = scanner.nextLine();
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private int setInterval() {
        System.out.println("Enter time interval");
        return scanner.nextInt();
    }
}
