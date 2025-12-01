// Name: Anjali Tomar
// Roll Number: 2401010080
package service;
import model.Student;
import util.FileUtil;
import exception.StudentNotFoundException;
import java.util.*;

public class StudentManager implements RecordActions {

    private List<Student> students;
    private Map<Integer, Student> studentMap;

    public StudentManager() {
        students = new ArrayList<>();
        studentMap = new HashMap<>();
    }

    public void load(String path) {
        students = FileUtil.readStudents(path);
        studentMap.clear();
        for (Student s : students) studentMap.put(s.getRollNo(), s);
        System.out.println("Loaded students from file: " + students.size());
    }

    public void save(String path) {
        FileUtil.writeStudents(path, students);
        System.out.println("Saved " + students.size() + " students to file.");
    }

    @Override
    public void addStudent(Student s) throws Exception {
        if (studentMap.containsKey(s.getRollNo())) throw new Exception("Duplicate roll number.");
        students.add(s);
        studentMap.put(s.getRollNo(), s);
    }

    @Override
    public void deleteStudentByName(String name) throws StudentNotFoundException {
        Iterator<Student> it = students.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name)) {
                it.remove();
                studentMap.remove(s.getRollNo());
                removed = true;
            }
        }
        if (!removed) throw new StudentNotFoundException("No student with name: " + name);
    }

    @Override
    public void updateStudent(int rollNo, Student updated) throws StudentNotFoundException {
        if (!studentMap.containsKey(rollNo)) throw new StudentNotFoundException(rollNo);
        Student s = studentMap.get(rollNo);
        s.inputDetails(rollNo, updated.getName(), updated.getEmail(), updated.getCourse(), updated.getMarks());
        // list already references same object; if different object used, update in list too
    }

    @Override
    public Student searchStudentByName(String name) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        throw new StudentNotFoundException("No student with name: " + name);
    }

    @Override
    public List<Student> viewAllStudents() {
        return new ArrayList<>(students);
    }

    public void sortByMarksDescending() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getMarks(), s1.getMarks());
            }
        });
    }

    public void sortByName() {
        Collections.sort(students, Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public String readRandom(String path, int lineNo) {
        return FileUtil.readRandomLine(path, lineNo);
    }
}
