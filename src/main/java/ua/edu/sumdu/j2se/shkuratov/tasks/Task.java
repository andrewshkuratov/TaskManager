package ua.edu.sumdu.j2se.shkuratov.tasks;

public class Task {
    private String title;

    private int time;

    private int start;
    private int end;
    private int interval;

    private boolean active;

    public Task(final String title, final int time) {
        if (time <= 0) {
            System.out.println("illegal value");
        } else {
            this.title = title;
            this.time = time;
            active = false;
        }
    }

    public Task(final String title, final int start,
                final int end, final int interval) {
        if (start < 0 || end < 0 || interval < 0) {
            System.out.println("illegal value");
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

    public void setTitle(final String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public void setTime(final int time) {
        this.time = time;
    }

    public int getStartTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public int getEndTime() {
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

    public void setTime(final int start, final int end, final int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        time = 0;
    }

    public boolean isRepeated() {
        return time == 0;
    }

    public int nextTimeAfter(final int current) {
        if (current < 0) {
            System.out.println("illegalValue");
        } else {
            if (isRepeated()) {
                if (isActive()) {
                    if (start > current) {
                        return start;
                    } else if (start <= current && current <= end) {
                        int i = (end - start) / interval;
                        for (int j = 0; j < i; j++) {
                            if ((start + j * interval) > current) {
                                return start + j * interval;
                            }
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {
                if (isActive()) {
                    if (current < time) {
                        return time;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }
}
