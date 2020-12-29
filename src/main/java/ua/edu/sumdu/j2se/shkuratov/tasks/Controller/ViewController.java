package ua.edu.sumdu.j2se.shkuratov.tasks.Controller;

import java.io.IOException;

public class ViewController {
    public void choosenOption(int i) throws IOException {
        String src = "/Users/andrew/Projects/Java/NCTaskManager/src/main/java/ua/edu/sumdu/j2se/shkuratov/tasks/TasksInfo";
        PrintTaskController printTaskController = new PrintTaskController();
        AddTaskController addTaskController = new AddTaskController();
        RemoveController removeController = new RemoveController();
        PrintCalenderController printCalenderController = new PrintCalenderController();
        EditTaskController editTaskController = new EditTaskController();

        switch (i) {
            case 1:
                printTaskController.printTaskInfo(src);
                break;
            case 2:
                addTaskController.addTask(src);
                break;
            case 3:
                editTaskController.chooseTask(src);
                break;
            case 4:
                removeController.removeTask(src);
                break;
            case 5:
                printCalenderController.printCalendar(src);
                break;
            default:
                System.out.println("Value out of range, please try again");
                break;
        }
    }
}
