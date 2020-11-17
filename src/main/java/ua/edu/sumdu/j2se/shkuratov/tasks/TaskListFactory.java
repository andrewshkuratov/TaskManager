package ua.edu.sumdu.j2se.shkuratov.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.LINKED) {
            return new LinkedTaskList();
        } else {
            return new ArrayTaskList();
        }
    }
}
