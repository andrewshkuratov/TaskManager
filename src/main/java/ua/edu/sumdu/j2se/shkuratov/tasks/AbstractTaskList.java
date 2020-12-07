package ua.edu.sumdu.j2se.shkuratov.tasks;

public abstract class AbstractTaskList implements Iterable<Task> {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to)
            throws IllegalArgumentException{
        if (from < 0 || to < 0 || from > to) {
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

        for (int i = 0; i < this.size(); i++) {
            if (getTask(i).nextTimeAfter(from) < to &&
                    getTask(i).nextTimeAfter(from) != -1) {
                abstractTaskList.add(getTask(i));
            }
        }
        return abstractTaskList;
    }
}
