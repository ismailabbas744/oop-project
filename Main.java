
package oop_project_3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final DataPersistence dataPersistence = new DataPersistence();
    private static final Scanner input = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = input.next();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    if (currentUser != null) {
                        userMenu();
                    }
                    break;
                case "3":
                    System.exit(0);
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println();
                default:
                    System.out.println("Invalid choice, please try again.");
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println();
            }
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter email: ");
        String email = input.nextLine();

        Student newStudent = new Student(username, password, email);
        dataPersistence.saveUser(newStudent);
        System.out.println("Registration successful.");
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        List<User> users = dataPersistence.getAllUsers();
        for (User user : users) {
            if (user.login(username, password)) {
                currentUser = user;
                System.out.println("Login successful.");
                System.out.println();
                System.out.println("-------------------------------------");
                System.out.println();
                return;
            }
        }
        System.out.println("Login failed. Invalid credentials.");
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();
    }

    private static void userMenu() {
        while (true) {
            System.out.println("1. Create Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    createTask();
                    break;
                case  "2":
                    updateTask();
                    break;
                case "3":
                    deleteTask();
                    break;
                case "4":
                    viewAllTasks();
                    break;
                case "5":
                    currentUser = null;
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println();
            }
        }
    }

    private static void createTask() {
        int taskId = dataPersistence.generateTaskId();
        System.out.print("Enter task name: ");
        String taskName = input.nextLine();
        System.out.print("Enter due date (dd-MM-yyyy): ");
        Date dueDate = parseDate(input.nextLine());
        System.out.print("Enter priority: ");
        int priority = Integer.parseInt(input.nextLine());

        Task newTask = new Task(taskId, taskName, dueDate, priority);
        dataPersistence.saveTask(newTask);
        System.out.println("Task created successfully.");
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();
    }

    private static void updateTask() 
    {
        System.out.print("Enter task ID to update: ");
        int taskId = Integer.parseInt(input.nextLine());
        Task task = dataPersistence.retrieveTask(taskId);
        if (task != null) {
            System.out.print("Enter new task name: ");
            task.setTaskName(input.nextLine());
            System.out.print("Enter new due date (dd-MM-yyyy): ");
            task.setDueDate(parseDate(input.nextLine()));
            System.out.print("Enter new priority: ");
            task.setPriority(Integer.parseInt(input.nextLine()));
            System.out.println("Task updated successfully.");
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println();
        }
        else 
        {
            System.out.println("Task not found.");
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println();
        }
    } 
    

    private static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        int taskId = Integer.parseInt(input.nextLine());
        dataPersistence.removeTask(taskId);
        System.out.println("Task deleted successfully.");
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();
    }

    private static void viewAllTasks() {
        List<Task> tasks = dataPersistence.getAllTasks();
        if (tasks.isEmpty()) 
        {
            System.out.println("No tasks found.");
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println();
        }
        else 
        {
            for (Task task : tasks) 
            {
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println(task);
                System.out.println("------------------------------------------------------------------------------------------------------");
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println();
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Using current date.");
            return new Date();
        }
    }
}
