
public class AdminSetup {

    public static void main(String[] args) {

        AdminDAO adminDAO = new AdminDAO();

        boolean setupResult =
                adminDAO.setupAdmin("admin", "admin123");

        System.out.println("Admin setup result: " + setupResult);

        if (setupResult) {

            System.out.println("Admin account is ready!");

            boolean loginTest =
                    adminDAO.authenticateAdmin("admin", "admin123");

            System.out.println("Direct login test: " + loginTest);

            if (loginTest) {
                System.out.println("SUCCESS: Password verification works!");
            } else {
                System.out.println("FAILED: Password verification has a problem!");
            }

        } else {
            System.out.println("Admin setup failed!");
        }
    }
}
