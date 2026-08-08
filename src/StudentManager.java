import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    // Add student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // Check duplicate ID
    public boolean studentIdExists(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return true;
            }
        }

        return false;
    }

    // View all students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            student.displayDetails();
        }
    }

    // Search student
    public void searchStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                student.displayDetails();
                return;
            }
        }

        System.out.println("Student not found!");
    }

    // Update student
    public void updateStudent(int id, String name, int age,
                              String course, double marks) {

        for (Student student : students) {

            if (student.getId() == id) {

                student.setName(name);
                student.setAge(age);
                student.setCourse(course);
                student.setMarks(marks);

                System.out.println("Student updated successfully!");
                return;
            }
        }

        System.out.println("Student not found!");
    }

    // Delete student
    public void deleteStudent(int id) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == id) {

                students.remove(i);

                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found!");
    }
}