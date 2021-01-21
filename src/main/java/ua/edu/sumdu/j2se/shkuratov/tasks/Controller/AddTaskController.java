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

public class AddTaskController {
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void addTask(String src) throws IOException {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        TaskIO.readText(arrayTaskList, new File(src));
        arrayTaskList.add(createTask());
        TaskIO.writeText(arrayTaskList, new File(src));
    }

    private Task createTask() {
        System.out.println("Is your task repeating?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int isRepeated = Integer.parseInt(scanner.nextLine());
        switch (isRepeated) {
            case 1:
                return repeatedTask();
            case 2:
                return nonRepeatedTask();
            default:
                System.out.println("Invalid option, please try again");
                return createTask();
        }
    }

    private Task repeatedTask() {
        String title = inputTitle();
        LocalDateTime dateTime = inputStartTime("Input task start date of format yyyy-MM-dd HH:mm : ");
        LocalDateTime endDateTime = inputStartTime("Input task end date of format yyyy-MM-dd HH:mm : ");
        int interval = inputTimeInterval();
        return new Task(title, dateTime, endDateTime, interval);
    }

    private Task nonRepeatedTask() {
        String title = inputTitle();
        LocalDateTime dateTime = inputStartTime("Input task start date of format yyyy-MM-dd HH:mm : ");
        return new Task(title, dateTime);
    }

    private String inputTitle() {
        System.out.print("Input task title : ");
        return scanner.nextLine();
    }

    private LocalDateTime inputStartTime(String data) {
        System.out.print(data);
        String date = scanner.nextLine();
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private int inputTimeInterval() {
        System.out.print("Input task repeat interval : ");
        return scanner.nextInt();
    }
}
