
public class TestDatabaseInsert {

    public static void main(String[] args) {

        Student student = new Student(
                1001,
                "Vasavi",
                22,
                "CSE",
                95.5
        );

        StudentDAO dao = new StudentDAO();

        dao.addStudent(student);
    }
}
