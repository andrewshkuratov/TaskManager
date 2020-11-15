package ua.edu.sumdu.j2se.shkuratov.tasks;

public class LinkedTaskList {
    private ListTask first;
    private ListTask last;
    private int size = 0;

    private static class ListTask {
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

    public boolean remove(Task element) {
        if (size == 0) {
            return false;
        } else if (first.element.equals(element)) {
            first = first.next;
            size--;
            return true;
        }

        ListTask listTask = findBeforeElement(element);

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

    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0 || from > to) {
            throw new IllegalArgumentException();
        }
        LinkedTaskList t = new LinkedTaskList();
        ListTask item = first;
        for (int i = 0; i < size; i++) {
            if (item.element.isActive()) {
                if (item.element.isRepeated()) {
                    if (item.element.nextTimeAfter(from) < to && item.element.nextTimeAfter(to) > from) {
                        t.add(item.element);
                        item = item.next;
                    }
                } else {
                    if (from < item.element.getTime() && to > item.element.getTime()) {
                        t.add(item.element);
                        item = item.next;
                    }
                }
            }
        }
        return t;
    }

    public void print() {
        ListTask listTask = first;
        for (int i = 0; i < size; i++) {
            System.out.println(listTask.element.getTitle());
            listTask = listTask.next;
        }
    }
}
