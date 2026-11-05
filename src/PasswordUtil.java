
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    public static String generateSalt() {

        byte[] salt = new byte[SALT_LENGTH];

        new SecureRandom().nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(
            String password,
            String salt
    ) {

        PBEKeySpec spec = null;

        try {
            byte[] saltBytes =
                    Base64.getDecoder().decode(salt);

            spec = new PBEKeySpec(
                    password.toCharArray(),
                    saltBytes,
                    ITERATIONS,
                    KEY_LENGTH
            );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance(
                            "PBKDF2WithHmacSHA256"
                    );

            byte[] hash =
                    factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder()
                    .encodeToString(hash);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Password hashing failed", e
            );

        } finally {
            if (spec != null) {
                spec.clearPassword();
            }
        }
    }

    public static boolean verifyPassword(
            String password,
            String storedHash,
            String storedSalt
    ) {

        try {
            String enteredHash =
                    hashPassword(password, storedSalt);

            byte[] enteredBytes =
                    Base64.getDecoder().decode(enteredHash);

            byte[] storedBytes =
                    Base64.getDecoder().decode(storedHash);

            return MessageDigest.isEqual(
                    enteredBytes,
                    storedBytes
            );

        } catch (Exception e) {
            return false;
        }
    }
}
