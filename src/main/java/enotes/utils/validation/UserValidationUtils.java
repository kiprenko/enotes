package enotes.utils.validation;

import enotes.data.user.User;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class UserValidationUtils {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidUser(User user) {
        if (isEmpty(user.getFirstName()) || isEmpty(user.getLastName())) {
            LOGGER.error("User has invalid first name or last name. First name = {} and last name = {}",
                    user.getFirstName(), user.getLastName());
            return false;
        } else if (isNotValidEmail(user.getEmail())) {
            LOGGER.error("User has invalid email. Email = {}", user.getEmail());
            return false;
        } else if (isNotValidPassword(user.getPassword())) {
            LOGGER.error("User has invalid password (null, empty, or less than 8 characters)");
            return false;
        } else if (isEmpty(user.getCountry())) {
            LOGGER.error("User has invalid country. Country = {}", user.getCountry());
            return false;
        } else if (user.getRegistration() == null) {
            LOGGER.error("User has registration date as null.");
            return false;
        } else if (user.getAge() == 0) {
            LOGGER.error("User's age equals 0.");
            return false;
        } else if(user.getRole() == null) {
            LOGGER.error("User's role equals null.");
            return false;
        }

        return true;
    }

    private static boolean isEmpty(String firstName) {
        return firstName == null || firstName.isEmpty();
    }

    private static boolean isNotValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return !matcher.find();
    }

    private static boolean isNotValidPassword(String password) {
        return password == null || password.isEmpty() || password.trim().length() < 8;
    }
}
