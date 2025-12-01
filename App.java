// Name: Anjali Tomar
// Roll Number: 2401010080
package main;
import service.StudentManager;
import model.Student;
import exception.StudentNotFoundException;
import util.Loader;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final String DATA_FILE = "students.txt";
    private static StudentManager manager = new StudentManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        manager.load(DATA_FILE);
        String rnd = manager.readRandom(DATA_FILE, 2);
        if (rnd != null) System.out.println("Random line 2: " + rnd);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": addStudent(); break;
                case "2": viewAll(); break;
                case "3": searchByName(); break;
                case "4": deleteByName(); break;
                case "5": sortByMarks(); break;
                case "6": saveAndExit(); running = false; break;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void printMenu() {
        System.out.println("===== Capstone Student Menu =====");
        System.out.println("===== Vanshika Dixit/2401010010=====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search by Name");
        System.out.println("4. Delete by Name");
        System.out.println("5. Sort by Marks");
        System.out.println("6. Save and Exit");
        System.out.print("Enter choice: ");
    }

    private static void addStudent() {
        try {
            System.out.print("Enter Roll No: ");
            int roll = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();
            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();
            System.out.print("Enter Course: ");
            String course = sc.nextLine().trim();
            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(sc.nextLine().trim());

            Student s = new Student(roll, name, email, course, marks);

            Thread t = new Thread(new Loader(6, 250));
            t.start();
            t.join();

            manager.addStudent(s);
            System.out.println("Student added successfully.");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAll() {
        List<Student> all = manager.viewAllStudents();
        if (all.isEmpty()) {
            System.out.println("No records.");
            return;
        }
        for (Student s : all) {
            System.out.println("--------------------");
            s.displayInfo();
        }
    }

    private static void searchByName() {
        try {
            System.out.print("Enter name to search: ");
            String name = sc.nextLine().trim();
            Student s = manager.searchStudentByName(name);
            System.out.println("Student Info:");
            s.displayInfo();
        } catch (StudentNotFoundException snf) {
            System.out.println(snf.getMessage());
        }
    }

    private static void deleteByName() {
        try {
            System.out.print("Enter name to delete: ");
            String name = sc.nextLine().trim();
            Thread t = new Thread(new Loader(4, 200));
            t.start();
            t.join();
            manager.deleteStudentByName(name);
            System.out.println("Student record deleted.");
        } catch (StudentNotFoundException snf) {
            System.out.println(snf.getMessage());
        }
    }

    private static void sortByMarks() {
        manager.sortByMarksDescending();
        System.out.println("Sorted Student List by Marks:");
        viewAll();
    }

    private static void saveAndExit() {
        manager.save(DATA_FILE);
        System.out.println("Saved and exiting.");
    }
}
