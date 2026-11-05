
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentGUI {

    private JFrame frame;
    private StudentDAO dao = new StudentDAO();

    private JLabel totalStudentsLabel;
    private JLabel averageMarksLabel;

    private final Color BACKGROUND = new Color(242, 245, 250);
    private final Color PRIMARY = new Color(35, 55, 90);
    private final Color BLUE = new Color(55, 105, 190);
    private final Color GREEN = new Color(40, 145, 110);
    private final Color ORANGE = new Color(220, 140, 45);
    private final Color RED = new Color(190, 65, 65);
    private final Color PURPLE = new Color(120, 80, 160);

    public StudentGUI() {

        frame = new JFrame("Student Management System");
        frame.setSize(950, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND);

        // Header
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(PRIMARY);
        header.setBorder(
                BorderFactory.createEmptyBorder(25, 20, 25, 20));

        JLabel title = new JLabel(
                "STUDENT MANAGEMENT SYSTEM",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
                "Manage Student Records Easily",
                SwingConstants.CENTER);

        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(new Color(220, 225, 235));

        header.add(title);
        header.add(subtitle);

        frame.add(header, BorderLayout.NORTH);

        // Dashboard statistics
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        statsPanel.setBackground(BACKGROUND);
        statsPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 100, 15, 100));

        totalStudentsLabel = new JLabel(
                "Total Students: 0", SwingConstants.CENTER);

        averageMarksLabel = new JLabel(
                "Average Marks: 0.00", SwingConstants.CENTER);

        totalStudentsLabel.setFont(
                new Font("Arial", Font.BOLD, 18));

        averageMarksLabel.setFont(
                new Font("Arial", Font.BOLD, 18));

        totalStudentsLabel.setForeground(BLUE);
        averageMarksLabel.setForeground(GREEN);

        statsPanel.add(totalStudentsLabel);
        statsPanel.add(averageMarksLabel);

        // Dashboard buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 2, 20, 20));
        buttonPanel.setBackground(BACKGROUND);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 100, 35, 100));

        JButton addButton = createButton("Add Student", GREEN);
        JButton viewButton = createButton("View Students", BLUE);
        JButton searchButton = createButton("Search Student", ORANGE);
        JButton updateButton = createButton("Update Student", PRIMARY);
        JButton deleteButton = createButton("Delete Student", RED);
        JButton reportButton = createButton("Performance Report", PURPLE);
        JButton exportButton = createButton("Export Report", GREEN);
        JButton logoutButton = createButton("Logout", ORANGE);
        JButton exitButton = createButton("Exit", Color.GRAY);

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(exitButton);

        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(BACKGROUND);

        dashboardPanel.add(statsPanel, BorderLayout.NORTH);
        dashboardPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(dashboardPanel, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel(
                "Java Swing | JDBC | MySQL",
                SwingConstants.CENTER);

        footer.setFont(new Font("Arial", Font.PLAIN, 13));
        footer.setForeground(Color.DARK_GRAY);
        footer.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 15, 10));

        frame.add(footer, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addStudentForm());
        viewButton.addActionListener(e -> viewStudents());
        searchButton.addActionListener(e -> searchStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        reportButton.addActionListener(e -> performanceReport());
        exportButton.addActionListener(e -> exportReport());
        logoutButton.addActionListener(e -> logout());
        exitButton.addActionListener(e -> frame.dispose());

        refreshDashboardStats();

        frame.setVisible(true);
    }

    // Create styled buttons
    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // Dashboard statistics
    private void refreshDashboardStats() {

        ArrayList<Student> students = dao.getAllStudents();

        int total = students.size();
        double totalMarks = 0;

        for (Student student : students) {
            totalMarks += student.getMarks();
        }

        double average = total == 0 ? 0 : totalMarks / total;

        totalStudentsLabel.setText(
                "Total Students: " + total);

        averageMarksLabel.setText(
                String.format("Average Marks: %.2f", average));
    }

    // Add Student
    private void addStudentForm() {

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField courseField = new JTextField();
        JTextField marksField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Course:"));
        panel.add(courseField);
        panel.add(new JLabel("Marks:"));
        panel.add(marksField);

        int result = JOptionPane.showConfirmDialog(
                frame, panel, "Add New Student",
                JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) return;

        try {

            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String course = courseField.getText().trim();
            double marks = Double.parseDouble(
                    marksField.getText().trim());

            if (id <= 0 || name.isEmpty() ||
                    age < 1 || age > 100 ||
                    course.isEmpty() ||
                    marks < 0 || marks > 100) {

                JOptionPane.showMessageDialog(frame,
                        "Please enter valid student details!");
                return;
            }

            if (dao.studentIdExists(id)) {
                JOptionPane.showMessageDialog(frame,
                        "Student ID already exists!");
                return;
            }

            Student student = new Student(
                    id, name, age, course, marks);

            if (dao.addStudent(student)) {

                JOptionPane.showMessageDialog(frame,
                        "Student added successfully!");

                refreshDashboardStats();

            } else {

                JOptionPane.showMessageDialog(frame,
                        "Unable to add student!");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(frame,
                    "ID, age and marks must be valid numbers!");
        }
    }

    // View Students
    private void viewStudents() {

        ArrayList<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "No students found!");
            return;
        }

        String[] columns = {
                "Student ID", "Name", "Age", "Course", "Marks"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Student student : students) {

            model.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getMarks()
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);

        JDialog dialog = new JDialog(
                frame, "All Students", true);

        dialog.add(scrollPane);
        dialog.setSize(700, 400);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    // Search Student
    private void searchStudent() {

        String input = JOptionPane.showInputDialog(
                frame, "Enter Student ID:",
                "Search Student", JOptionPane.QUESTION_MESSAGE);

        if (input == null) return;

        try {

            int id = Integer.parseInt(input.trim());
            Student student = dao.searchStudent(id);

            if (student != null) {

                String details =
                        "Student ID: " + student.getId() +
                                "\nName: " + student.getName() +
                                "\nAge: " + student.getAge() +
                                "\nCourse: " + student.getCourse() +
                                "\nMarks: " + student.getMarks();

                JOptionPane.showMessageDialog(
                        frame, details, "Student Details",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(frame,
                        "Student not found!");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(frame,
                    "Enter a valid Student ID!");
        }
    }

    // Update Student
    private void updateStudent() {

        String input = JOptionPane.showInputDialog(
                frame, "Enter Student ID to update:");

        if (input == null) return;

        try {

            int id = Integer.parseInt(input.trim());

            if (id <= 0) {
                JOptionPane.showMessageDialog(frame,
                        "Enter a valid positive ID!");
                return;
            }

            Student student = dao.searchStudent(id);

            if (student == null) {
                JOptionPane.showMessageDialog(frame,
                        "Student not found!");
                return;
            }

            JTextField nameField =
                    new JTextField(student.getName());

            JTextField ageField =
                    new JTextField(String.valueOf(student.getAge()));

            JTextField courseField =
                    new JTextField(student.getCourse());

            JTextField marksField =
                    new JTextField(String.valueOf(student.getMarks()));

            JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Age:"));
            panel.add(ageField);
            panel.add(new JLabel("Course:"));
            panel.add(courseField);
            panel.add(new JLabel("Marks:"));
            panel.add(marksField);

            int result = JOptionPane.showConfirmDialog(
                    frame, panel, "Update Student",
                    JOptionPane.OK_CANCEL_OPTION);

            if (result != JOptionPane.OK_OPTION) return;

            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String course = courseField.getText().trim();
            double marks = Double.parseDouble(
                    marksField.getText().trim());

            if (name.isEmpty() || age < 1 || age > 100 ||
                    course.isEmpty() || marks < 0 || marks > 100) {

                JOptionPane.showMessageDialog(frame,
                        "Please enter valid details!");
                return;
            }

            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
            student.setMarks(marks);

            if (dao.updateStudent(student)) {

                JOptionPane.showMessageDialog(frame,
                        "Student updated successfully!");

                refreshDashboardStats();

            } else {

                JOptionPane.showMessageDialog(frame,
                        "Unable to update student!");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(frame,
                    "Please enter valid numeric values!");
        }
    }

    // Delete Student
    private void deleteStudent() {

        String input = JOptionPane.showInputDialog(
                frame, "Enter Student ID to delete:");

        if (input == null) return;

        try {

            int id = Integer.parseInt(input.trim());

            if (id <= 0) {
                JOptionPane.showMessageDialog(frame,
                        "Enter a valid positive ID!");
                return;
            }

            Student student = dao.searchStudent(id);

            if (student == null) {
                JOptionPane.showMessageDialog(frame,
                        "Student not found!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete:\n" +
                            student.getName() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {

                if (dao.deleteStudent(id)) {

                    JOptionPane.showMessageDialog(frame,
                            "Student deleted successfully!");

                    refreshDashboardStats();

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Unable to delete student!");
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(frame,
                    "Please enter a valid Student ID!");
        }
    }

    // Performance Report with Grade and Pass/Fail
    private void performanceReport() {

        ArrayList<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "No student records available!");
            return;
        }

        String[] columns = {
                "Student ID", "Name", "Marks", "Grade", "Result"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        int passCount = 0;
        int failCount = 0;

        for (Student student : students) {

            double marks = student.getMarks();
            String grade;
            String result;

            if (marks >= 90) {
                grade = "A";
            } else if (marks >= 80) {
                grade = "B";
            } else if (marks >= 70) {
                grade = "C";
            } else if (marks >= 60) {
                grade = "D";
            } else if (marks >= 40) {
                grade = "E";
            } else {
                grade = "F";
            }

            if (marks >= 40) {
                result = "Pass";
                passCount++;
            } else {
                result = "Fail";
                failCount++;
            }

            model.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    marks,
                    grade,
                    result
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);

        JLabel summary = new JLabel(
                "Total: " + students.size() +
                        "    |    Pass: " + passCount +
                        "    |    Fail: " + failCount,
                SwingConstants.CENTER);

        summary.setFont(new Font("Arial", Font.BOLD, 16));
        summary.setBorder(
                BorderFactory.createEmptyBorder(12, 10, 12, 10));

        summary.setForeground(PRIMARY);

        JDialog dialog = new JDialog(
                frame, "Student Performance Report", true);

        dialog.setLayout(new BorderLayout());
        dialog.add(summary, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);

        dialog.setSize(750, 450);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    // Export Performance Report to CSV
    private void exportReport() {

        ArrayList<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "No student records available!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle(
                "Save Student Performance Report");

        fileChooser.setSelectedFile(
                new File("Student_Performance_Report.csv"));

        int choice = fileChooser.showSaveDialog(frame);

        if (choice != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        if (!file.getName().toLowerCase().endsWith(".csv")) {
            file = new File(file.getAbsolutePath() + ".csv");
        }

        if (file.exists()) {

            int overwrite = JOptionPane.showConfirmDialog(
                    frame,
                    "File already exists. Do you want to overwrite it?",
                    "Confirm Overwrite",
                    JOptionPane.YES_NO_OPTION
            );

            if (overwrite != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try (FileWriter writer = new FileWriter(file)) {

            writer.write(
                    "Student ID,Name,Age,Course,Marks,Grade,Result\n");

            for (Student student : students) {

                double marks = student.getMarks();

                String grade;
                String result;

                if (marks >= 90) {
                    grade = "A";
                } else if (marks >= 80) {
                    grade = "B";
                } else if (marks >= 70) {
                    grade = "C";
                } else if (marks >= 60) {
                    grade = "D";
                } else if (marks >= 40) {
                    grade = "E";
                } else {
                    grade = "F";
                }

                result = marks >= 40 ? "Pass" : "Fail";

                writer.write(
                        student.getId() + "," +
                                "\"" + student.getName().replace("\"", "\"\"") + "\"," +
                                student.getAge() + "," +
                                "\"" + student.getCourse().replace("\"", "\"\"") + "\"," +
                                marks + "," +
                                grade + "," +
                                result + "\n"
                );
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Performance report exported successfully!\n" +
                            file.getAbsolutePath()
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Error exporting report: " + e.getMessage());
        }
    }

    // Logout
    private void logout() {

        int choice = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            frame.dispose();

            new Login();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Login());
    }
}
