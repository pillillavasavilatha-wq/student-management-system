
import java.util.ArrayList;

public class StudentManager {

    private StudentDAO dao = new StudentDAO();

    // Check duplicate ID
    public boolean studentIdExists(int id) {
        return dao.studentIdExists(id);
    }

    // Add student
    public void addStudent(Student student) {

        if (dao.studentIdExists(student.getId())) {
            System.out.println("Student ID already exists!");
            return;
        }

        if (dao.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Unable to add student.");
        }
    }

    // View all students
    public void viewStudents() {

        ArrayList<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n========== All Students ==========");

        for (Student student : students) {
            student.displayDetails();
        }
    }

    // Search student
    public void searchStudent(int id) {

        Student student = dao.searchStudent(id);

        if (student != null) {
            student.displayDetails();
        } else {
            System.out.println("Student not found!");
        }
    }

    // Update student
    public void updateStudent(int id, String name, int age,
                              String course, double marks) {

        Student student = dao.searchStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        student.setMarks(marks);

        if (dao.updateStudent(student)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Unable to update student.");
        }
    }

    // Delete student
    public void deleteStudent(int id) {

        if (dao.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found or deletion failed.");
        }
    }
}
