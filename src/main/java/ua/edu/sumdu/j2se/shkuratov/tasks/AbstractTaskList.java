package ua.edu.sumdu.j2se.shkuratov.tasks;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    public abstract Stream<Task> getStream();

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to)
            throws IllegalArgumentException {
        if (from == null || to == null || from.isAfter(to)) {
            throw new IllegalArgumentException();
        }

        AbstractTaskList abstractTaskList;

        if (this.getClass().getSimpleName().equals("ArrayTaskList")) {
            abstractTaskList = TaskListFactory.
                    createTaskList(ListTypes.types.ARRAY);
        } else {
            abstractTaskList = TaskListFactory.
                    createTaskList(ListTypes.types.LINKED);
        }

        getStream()
                .filter(x ->
                        x.nextTimeAfter(from).isBefore(to) &&
                        x.nextTimeAfter(from) != null)
                .forEach(abstractTaskList::add);
        return abstractTaskList;
    }
}
