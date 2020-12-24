package ua.edu.sumdu.j2se.shkuratov.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Tasks implements Serializable {
    public static Iterable<Task> incoming(Iterable<Task> tasks,
                                          LocalDateTime start,
                                          LocalDateTime end) {
        AbstractTaskList temp = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        for(Task task : tasks) {
            if (task.nextTimeAfter(start).isBefore(end) &&
                    task.nextTimeAfter(start) != null) {
                temp.add(task);
            }
        }
        return temp;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks,
                                                      LocalDateTime start,
                                                      LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<>();
        for (Task task : tasks) {
            if (!task.isActive()) {
                continue;
            }

            LocalDateTime next = task.nextTimeAfter(start);
            if (next == null) {
                continue;
            }
            if (next.isAfter(end)) {
                continue;
            }

            if (task.isRepeated()) {
                LocalDateTime t = next;
                while (t.isBefore(end)) {
                    if (map.containsKey(t)) {
                        map.get(t).add(task);
                    } else {
                        Set set = new HashSet<Task>();
                        set.add(task);
                        map.put(t, set);
                    }
                    t = t.plusMinutes(task.getRepeatInterval());
                }
            } else {
                if (map.containsKey(task.getTime())) {
                    map.get(task.getTime()).add(task);
                } else {
                    Set set = new HashSet<Task>();
                    set.add(task);
                    map.put(task.getTime(), set);
                }
            }
        }
        return map;
    }
}
