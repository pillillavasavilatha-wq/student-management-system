
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public boolean setupAdmin(
            String username,
            String password
    ) {

        String checkSql =
                "SELECT id FROM admins WHERE username = ?";

        String insertSql =
                "INSERT INTO admins " +
                        "(username, password_hash, password_salt) " +
                        "VALUES (?, ?, ?)";

        String updateSql =
                "UPDATE admins SET password_hash = ?, " +
                        "password_salt = ? WHERE username = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            String salt = PasswordUtil.generateSalt();

            String hash =
                    PasswordUtil.hashPassword(password, salt);

            try (PreparedStatement check =
                         con.prepareStatement(checkSql)) {

                check.setString(1, username);

                try (ResultSet rs = check.executeQuery()) {

                    if (rs.next()) {

                        try (PreparedStatement update =
                                     con.prepareStatement(updateSql)) {

                            update.setString(1, hash);
                            update.setString(2, salt);
                            update.setString(3, username);

                            return update.executeUpdate() > 0;
                        }
                    }
                }
            }

            try (PreparedStatement insert =
                         con.prepareStatement(insertSql)) {

                insert.setString(1, username);
                insert.setString(2, hash);
                insert.setString(3, salt);

                return insert.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Admin setup failed!");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean authenticateAdmin(
            String username,
            String password
    ) {

        String sql =
                "SELECT password_hash, password_salt " +
                        "FROM admins WHERE username = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        String storedHash =
                                rs.getString("password_hash");

                        String storedSalt =
                                rs.getString("password_salt");

                        return PasswordUtil.verifyPassword(
                                password,
                                storedHash,
                                storedSalt
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Login authentication failed!");
            System.out.println(e.getMessage());
        }

        return false;
    }
}
