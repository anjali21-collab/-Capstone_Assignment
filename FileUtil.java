// Name: Anjali Tomar
// Roll Number: 2401010080
package util;
import model.Student;
import java.io.*;
import java.util.*;

public class FileUtil {

    public static List<Student> readStudents(String path) {
        List<Student> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromCSV(line);
                if (s != null) list.add(s);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static void writeStudents(String path, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static String readRandomLine(String path, int lineNumber) {
        File f = new File(path);
        if (!f.exists()) return null;
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            String line;
            int current = 1;
            raf.seek(0);
            while ((line = raf.readLine()) != null) {
                if (current == lineNumber) return line;
                current++;
            }
        } catch (IOException e) {
            System.out.println("Random read error: " + e.getMessage());
        }
        return null;
    }
}
