package Form;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joe Lombardi on 4/29/2017.
 */
public class StringParsing {

    private static String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z0-9]{2,})$";
    private static String phonePattern = "(\\d{10})|((\\(\\d{3})\\)[-.\\s](\\d{3})[-.\\s](\\d{4}))|((\\d{3})[-.\\s](\\d{3})[-.\\s](\\d{4}))";
    private static String serialPattern = "^(\\d{2})-([a-zA-Z\\d]{4})$";
    private static String idPattern = "^([0-9]{4,5})+([A-Za-z0-9])$";
    private static String permitPattern = "^([A-Z]{2})-([A-Z]{1,3})-([A-Z]{1,3}-){0,1}(\\d{4,5})$";

    /**
     * Function takes in a string, checks if it is a valid email address e.g. abc123@abc123.com
     *
     * @param email
     * @return BOOLEAN
     */
    public static boolean emailValidation(String email) {
        return checkStringWithPattern(email, emailPattern);
    }

    /**
     * Function takes in string, checks if it is valid phone number, e.g. ###-###-####, (###) ### ####, etc.
     *
     * @param number
     * @return BOOLEAN
     */
    public static boolean phoneNmbrValidation(String number) {
        return checkStringWithPattern(number, phonePattern);
    }

    /**
     * Function takes in string, checks whether it is ##-####
     *
     * @param serial
     * @return BOOLEAN
     */
    public static boolean serialValidation(String serial) {
        return checkStringWithPattern(serial, serialPattern);
    }

    /**
     * Function takes string, checks whether it is valid ID
     *
     * @param id
     * @return BOOLEAN
     */
    public static boolean repIDValidation(String id) {
        return checkStringWithPattern(id, idPattern);
    }

    /**
     * Function takes string, checks whether it is valid permit. Takes pattern of AZ-AZ-AZZ-12345 or AZ-AZ-1234, etc
     *
     * @param permit
     * @return BOOLEAN
     */
    public static boolean permitValidation(String permit) {
        return checkStringWithPattern(permit, permitPattern);
    }


    /**
     * Helper function to remove redundant code. Takes string to check, and pattern to look at.
     *
     * @param string
     * @param pattern
     * @return BOOLEAN
     */
    static boolean checkStringWithPattern(String string, String pattern) {

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(string);

        return matcher.matches();
    }
}
