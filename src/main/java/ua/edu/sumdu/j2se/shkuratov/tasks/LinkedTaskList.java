package ua.edu.sumdu.j2se.shkuratov.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList {
    private ListTask first;
    private ListTask last;
    private int size = 0;

    public class ListTask {
        Task element;
        ListTask next;
        ListTask previous;

        public ListTask(Task element) {
            this.element = element;
        }
    }

    public void add(Task task) {
        ListTask listTask = new ListTask(task);
        if (first == null) {
            listTask.next = null;
            listTask.previous = null;
            first = listTask;
            last = listTask;
        } else {
            last.next = listTask;
            listTask.previous = last;
            last = listTask;
        }
        size++;
    }

    public boolean remove(Task task) {
        if (size == 0) {
            return false;
        } else if (first.element.equals(task)) {
            first = first.next;
            size--;
            return true;
        }

        ListTask listTask = findBeforeElement(task);

        if (listTask != null) {
            if (listTask.next.next != null) {
                listTask.next = listTask.next.next;
            } else {
                listTask.next = null;
            }
            size--;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListTask result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }

        return result.element;
    }

    private ListTask findBeforeElement(Task value) {
        ListTask listTask = first;
        while (listTask.next != null) {
            if (listTask.next.element.equals(value)) {
                return listTask;
            }
            listTask = listTask.next;
        }
        return null;
    }

    public void print() {
        ListTask listTask = first;
        for (int i = 0; i < size; i++) {
            System.out.println(listTask.element.getTitle());
            listTask = listTask.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            Task task = getTask(i);
            data.append(task.toString());
        }
        return data.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        if(that.size() == this.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (!this.getTask(i).equals(that.getTask(i))) {
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
        return Objects.hash(first, last, size);
    }

    @Override
    protected LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList list = new LinkedTaskList();
        for (int i = 0; i < this.size(); i++) {
            list.add(this.getTask(i));
        }
        return list;
    }

    @Override
    public @NotNull Iterator<Task> iterator() {
        Iterator<Task> iterator = new Iterator<Task>() {
            private int lastRet = -1; // index of last element returned; -1 if no such
            private int currentIndex = 0;


            @Override
            public boolean hasNext() {
                return currentIndex < size() && getTask(currentIndex) != null;
            }

            @Override
            public Task next() {
                lastRet = currentIndex;
                return getTask(currentIndex++);
            }

            @Override
            public void remove() {
                if (currentIndex > 0) {
                    LinkedTaskList.this.remove(getTask(lastRet));
                    currentIndex--;
                }else {
                    throw new IllegalStateException();
                }
            }
        };
        return iterator;
    }
}
