// Name: Anjali Tomar
// Roll Number: 2401010080
package service;
import model.Student;
import exception.StudentNotFoundException;
import java.util.List;

public interface RecordActions {
    void addStudent(Student s) throws Exception;
    void deleteStudentByName(String name) throws StudentNotFoundException;
    void updateStudent(int rollNo, Student updated) throws StudentNotFoundException;
    Student searchStudentByName(String name) throws StudentNotFoundException;
    List<Student> viewAllStudents();
}
