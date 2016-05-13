package app.wishzee.com.wishzee.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ajit Gupta on 5/13/2016.
 */
public class Utility {

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
