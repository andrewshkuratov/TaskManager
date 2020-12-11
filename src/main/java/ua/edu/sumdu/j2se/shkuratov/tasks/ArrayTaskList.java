package ua.edu.sumdu.j2se.shkuratov.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {
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

    public Task getTask(int index)
            throws IndexOutOfBoundsException {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks[index];
    }

    @Override
    public Stream<Task> getStream() {
        return Stream.of(this.toArray());
    }

    private Task[] toArray() {
        Task[] tasks1 = new Task[size()];
        for (int i = 0; i < size(); i++) {
            tasks1[i] = tasks[i];
        }
        return tasks1;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            Task task = tasks[i];
            data.append(task.toString());
        }
        return data.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        if(that.size() == this.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (!this.tasks[i].equals(that.tasks[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tasks);
    }

    @Override
    protected ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList list = new ArrayTaskList();
        for (int i = 0; i < this.size(); i++) {
            list.add(this.getTask(i));
        }
        return list;
    }

    @Override
    public @NotNull Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int lastRet = -1; // index of last element returned; -1 if no such
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size() && tasks[currentIndex] != null;
            }

            @Override
            public Task next() {
                lastRet = currentIndex;
                return tasks[currentIndex++];
            }

            @Override
            public void remove() {
                if (currentIndex > 0) {
                    ArrayTaskList.this.remove(tasks[lastRet]);
                    currentIndex--;
                }else {
                    throw new IllegalStateException();
                }
            }
        };
    }
}
