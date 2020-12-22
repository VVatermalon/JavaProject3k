package by.osadchuk.students_base.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(int id) {
        super("Could not find user " + id);
    }
}