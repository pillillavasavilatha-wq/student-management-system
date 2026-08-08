import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {

            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    int id;

                    while (true) {

                        System.out.print("Enter Student ID: ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid ID! Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        id = sc.nextInt();

                        if (id <= 0) {
                            System.out.println("ID must be greater than 0.");
                            continue;
                        }

                        if (!manager.studentIdExists(id)) {
                            break;
                        }

                        System.out.println("Student ID already exists!");
                        System.out.println("Please enter a different ID.");
                    }
                    sc.nextLine();

                    String name;

                    while (true) {

                        System.out.print("Enter Student Name: ");
                        name = sc.nextLine().trim();

                        if (!name.isEmpty()) {
                            break;
                        }

                        System.out.println("Name cannot be empty!");
                    }

                    int age;

                    while (true) {

                        System.out.print("Enter Age (1-100): ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid age! Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        age = sc.nextInt();

                        if (age >= 1 && age <= 100) {
                            break;
                        }

                        System.out.println("Invalid age! Please enter between 1 and 100.");
                    }
                    sc.nextLine();

                    String course;

                    while (true) {

                        System.out.print("Enter Course: ");
                        course = sc.nextLine().trim();

                        if (!course.isEmpty()) {
                            break;
                        }

                        System.out.println("Course cannot be empty!");
                    }
                    double marks;

                    while (true) {

                        System.out.print("Enter Marks (0-100): ");

                        if (!sc.hasNextDouble()) {
                            System.out.println("Invalid marks! Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        marks = sc.nextDouble();

                        if (marks >= 0 && marks <= 100) {
                            break;
                        }

                        System.out.println("Invalid marks! Please enter between 0 and 100.");
                    }

                    Student student = new Student(
                            id, name, age, course, marks
                    );

                    manager.addStudent(student);

                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchId = sc.nextInt();

                    manager.searchStudent(searchId);
                    break;

                case 4:
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter New Course: ");
                    String newCourse = sc.nextLine();

                    System.out.print("Enter New Marks: ");
                    double newMarks = sc.nextDouble();

                    manager.updateStudent(
                            updateId,
                            newName,
                            newAge,
                            newCourse,
                            newMarks
                    );

                    break;

                case 5:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();

                    manager.deleteStudent(deleteId);
                    break;

                case 6:
                    System.out.println("Thank you for using Student Management System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}