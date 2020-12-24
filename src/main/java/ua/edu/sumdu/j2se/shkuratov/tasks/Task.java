package ua.edu.sumdu.j2se.shkuratov.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Serializable {
    private String title;

    private LocalDateTime time;

    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;

    private boolean active;

    public Task(String title, LocalDateTime time)
            throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("Values less then 0");
        } else {
            this.title = title;
            this.time = time;
            active = false;
            interval = 0;
        }
    }

    public Task(String title, LocalDateTime start,
                LocalDateTime end, int interval) throws IllegalArgumentException {
        if (start == null || end == null || interval <= 0) {
            throw new IllegalArgumentException("Values less then 0");
        } else {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            active = false;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public LocalDateTime getEndTime() {
        if (isRepeated()) {
            return end;
        }
        return time;
    }

    public int getRepeatInterval() {
        if (isRepeated()) {
            return interval;
        }
        return 0;
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        this.start = start;
        this.time = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return interval > 0;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null) {
            System.out.println("illegalValue");
        } else {
            if (!isActive()) {
                return null;
            }

            if (isRepeated()) {
                if (start.isAfter(current)) {
                    return start;
                } else if(end.isBefore(current)) {
                    return null;
                } else {
                    LocalDateTime temp = start;
                    while (temp.isBefore(end) || temp.isEqual(end)) {
                        if (current.isBefore(temp)) {
                            return temp;
                        }
                        temp = temp.plusSeconds(interval);
                    }
                }
            } else {
                if (time.isAfter(current)) {
                    return time;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                active == task.active &&
                Objects.equals(title, task.title);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
