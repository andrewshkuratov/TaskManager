package ua.edu.sumdu.j2se.shkuratov.tasks.Controller;

import ua.edu.sumdu.j2se.shkuratov.tasks.Model.ArrayTaskList;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.Task;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.TaskIO;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintTaskController {
    private Logger logger;

    public void printTaskInfo(String src) throws IOException {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        TaskIO.readText(arrayTaskList, new File(src));
        int i = 1;
        for (Task task : arrayTaskList) {
            System.out.println(i + ". " + task);
            i++;
        }
        logger.log(Level.INFO, "Tasks printed");
    }
}
