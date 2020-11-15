package ua.edu.sumdu.j2se.shkuratov.tasks;

public class ArrayTaskList {
    private Task[] tasks = new Task[100];

    public void add(Task task) {
        if (size() != tasks.length) {
            if (size() == 0) {
                tasks[0] = task;
            } else {
                tasks[size()] = task;
            }
            return;
        }
        Task[] t = new Task[tasks.length + 100];
        System.arraycopy(tasks, 0, t, 0, tasks.length);
        t[tasks.length] = task;
        tasks = t;
    }

    public boolean remove(Task task) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (tasks[i].equals(task)) {
                index = i;
                break;
            }
        }

        if (index == -1)
            return false;


        Task[] t = new Task[tasks.length];
        for (int j = 0, k = 0; j < size(); j++) {
            if (index != j)
                t[k++] = tasks[j];
        }

        tasks = t;

        return true;
    }

    public int size() {
        if (tasks[0] == null)  {
            return 0;
        }
        for (int i = 1; i < tasks.length; i++) {
            if (tasks[i] == null)
                return i;
        }
        return tasks.length;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList t = new ArrayTaskList();
        for (int i = 0; i < size(); i++) {
            if (tasks[i].isActive()) {
                if (tasks[i].isRepeated()) {
                    if (tasks[i].nextTimeAfter(from) < to && tasks[i].nextTimeAfter(to) > from) {
                        t.add(tasks[i]);
                    }
                } else {
                    if (from < tasks[i].getTime() && to > tasks[i].getTime()) {
                        t.add(tasks[i]);
                    }
                }
            }
        }
        return t;
    }
}
