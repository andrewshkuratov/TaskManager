package ua.edu.sumdu.j2se.shkuratov.tasks.Controller;

import ua.edu.sumdu.j2se.shkuratov.tasks.Model.ArrayTaskList;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.Task;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.TaskIO;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.Tasks;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintCalenderController {
    private Logger logger;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void printCalendar(String src) throws IOException {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        TaskIO.readText(arrayTaskList, new File(src));
        LocalDateTime dateTime = inputStartTime("Input task start date of format yyyy-MM-dd HH:mm : ");
        LocalDateTime endDateTime = inputStartTime("Input task end date of format yyyy-MM-dd HH:mm : ");
        if (dateTime == null || endDateTime == null || dateTime.isAfter(endDateTime)) {
            System.out.println("Invalid time entered");
            return;
        }
        SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(arrayTaskList, dateTime, endDateTime);
        Iterator iterator = calendar.keySet().iterator();
        while (iterator.hasNext()) {
            LocalDateTime key = (LocalDateTime) iterator.next();
            System.out.println(key);
            Set<Task> value = (Set<Task>) calendar.get(key);
            for (Task t : value) {
                if (key.isAfter(t.getEndTime())) {
                    return;
                }
                System.out.println(value);
            }
        }
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
}
