
package oop_project_3;



import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private int taskId;
    private String taskName;
    private Date dueDate;
    private int priority;
    private String status;

    public Task(int taskId, String taskName, Date dueDate, int priority) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = "Incomplete"; // Default status
    }

    // Getters and setters for task attributes
    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() 
    {
        
        return "|" + "Task ID: " + taskId + ", Task Name: " + taskName + ", Due Date: " + dueDate + ", Priority: " + priority + ", Status: " + status + "|";
    }
}
