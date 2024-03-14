package repository;

import models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static TaskRepository instance;
    private List<Task> taskList;
    private static int id = 1;

    private TaskRepository() {
        taskList = new ArrayList<>();
    }

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    public void addTask(Task task) {
        task.setId(id++);
        taskList.add(task);
    }

    public Task getTaskById(int id) {
        if (!taskList.isEmpty()) {
            for (Task t : taskList) {
                if (t.getId() == id) {
                    return t;
                }
            }
        }
        return null;
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!taskList.isEmpty()) {
            for (Task t : taskList) {
                stringBuilder.append(t.getId())
                        .append(". ")
                        .append(t.getTitle())
                        .append(" ")
                        .append(t.getDeadline())
                        .append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
