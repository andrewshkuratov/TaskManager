package ua.edu.sumdu.j2se.shkuratov.tasks.Model;

import ua.edu.sumdu.j2se.shkuratov.tasks.Model.AbstractTaskList;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.ArrayTaskList;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.LinkedTaskList;
import ua.edu.sumdu.j2se.shkuratov.tasks.Model.ListTypes;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.LINKED) {
            return new LinkedTaskList();
        } else {
            return new ArrayTaskList();
        }
    }
}
