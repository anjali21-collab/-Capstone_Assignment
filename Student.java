// Name: Anjali Tomar
// Roll Number: 2401010080
package model;
public class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private char grade;

    public Student() {}

    public Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    public void inputDetails(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    private void calculateGrade() {
        if (marks >= 90) grade = 'A';
        else if (marks >= 75) grade = 'B';
        else if (marks >= 60) grade = 'C';
        else grade = 'D';
    }

    @Override
    public void displayInfo() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }

    public int getRollNo() { return rollNo; }
    public double getMarks() { return marks; }
    public String getCourse() { return course; }
    public String getName() { return name; }

    public String toCSV() {
        return rollNo + "," + name.replace(",",";") + "," + email.replace(",",";") + "," + course.replace(",",";") + "," + marks;
    }

    public static Student fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] p = line.split(",", -1);
        if (p.length < 5) return null;
        try {
            int roll = Integer.parseInt(p[0].trim());
            String name = p[1].trim().replace(";",",");
            String email = p[2].trim().replace(";",",");
            String course = p[3].trim().replace(";",",");
            double marks = Double.parseDouble(p[4].trim());
            return new Student(roll, name, email, course, marks);
        } catch (Exception e) {
            return null;
        }
    }
}
