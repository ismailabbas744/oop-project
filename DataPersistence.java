
package oop_project_3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence {
    private static final String TASK_FILE = "tasks.dat";
    private static final String USER_FILE = "users.dat";
    private static int lastTaskId = 0;
    
    // Generate unique task ID
   public int generateTaskId() {
        lastTaskId++;
        return lastTaskId;
    }

    // Save a task to the file
    public void saveTask(Task task) {
        List<Task> tasks = getAllTasks();
        tasks.add(task);
        writeTasksToFile(tasks);
    }

    // Retrieve a task by ID
    public Task retrieveTask(int taskId) {
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    // Remove a task by ID
    public void removeTask(int taskId) {
        List<Task> tasks = getAllTasks();
        tasks.removeIf(task -> task.getTaskId() == taskId);
        writeTasksToFile(tasks);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASK_FILE))) {
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Write tasks to file
    private void writeTasksToFile(List<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASK_FILE))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save a user to the file
    public void saveUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        writeUsersToFile(users);
    }

    // Get all users
    public List<User> getAllUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Write users to file
    private void writeUsersToFile(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
