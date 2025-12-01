// Name: Anjali Tomar
// Roll Number: 2401010080
package exception;
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(int rollNo) {
        super("Student with roll number " + rollNo + " not found.");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
