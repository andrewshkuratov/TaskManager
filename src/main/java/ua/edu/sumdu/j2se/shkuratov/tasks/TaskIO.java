package ua.edu.sumdu.j2se.shkuratov.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TaskIO {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        try (DataOutputStream output = new DataOutputStream(new BufferedOutputStream(out))) {
            output.write(tasks.size());
            for (Task task : tasks) {
                output.writeInt(task.getTitle().length());
                output.writeUTF(task.getTitle());
                output.writeInt(task.isActive() ? 1 : 0);
                output.writeInt(task.getRepeatInterval());
                if (task.getRepeatInterval() != 0) {
                    output.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    output.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                } else {
                    output.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                }
            }
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        try (DataInputStream stream = new DataInputStream(new BufferedInputStream(in))) {
            int size = stream.readInt();
            for (int i = 0; i < size; i++) {
                Task task;
                LocalDateTime time;
                LocalDateTime endT;
                String title = stream.readUTF();
                int isActive = stream.readInt();
                int interval = stream.readInt();

                boolean active = (isActive == 1);

                time = LocalDateTime.ofEpochSecond(stream.readLong(), 0, ZoneOffset.UTC);

                if (interval != 0) {
                    endT = LocalDateTime.ofEpochSecond(stream.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(title, time, endT, interval);
                } else {
                    task = new Task(title, time);
                }
                tasks.add(task);
                task.setActive(active);
            }
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
            write(tasks, stream);
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file))) {
            read(tasks, stream);
        }
    }


    //Write to file area
    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        String json = new Gson().toJson(tasks);
        try (BufferedWriter write = new BufferedWriter(out)) {
            write.write(json);
            write.flush();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        try (BufferedReader reader = new BufferedReader(in)) {
            String data;
            while((data = reader.readLine()) != null) {
                AbstractTaskList abstractTaskList = new Gson().fromJson(data, tasks.getClass());
                for (Task task : abstractTaskList) {
                    tasks.add(task);
                }
            }
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        String json = new Gson().toJson(tasks);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(json);
            writer.flush();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;
            while ((text = reader.readLine()) != null) {
                AbstractTaskList taskList = new Gson().fromJson(text, tasks.getClass());
                for (Task task : taskList) {
                    tasks.add(task);
                }
            }
        }
    }
}
