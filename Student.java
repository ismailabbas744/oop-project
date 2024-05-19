
package oop_project_3;

public class Student extends User {
    public Student(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public boolean login(String username, String password) {
        return getUsername().equals(username) && getPassword().equals(password);
    }
}
