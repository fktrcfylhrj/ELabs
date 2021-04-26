import java.util.regex.Pattern;

/**
 * Class Checker checks the input parameter for compliance with some template
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class Checker {
    /*Class consists of static methods, that return true, if the input parameter
    for compliance with some template*/

    /**Returns true, if input string is word, email or phone number*/
    static boolean checkWord(String str){
        String simpleWord = "[A-Za-z]+-?(\\w*)";
        String phoneNumber = "\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}";
        String email = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        return Pattern.matches(simpleWord, str) ||
                Pattern.matches(phoneNumber, str) || Pattern.matches(email, str);
    }

    /**Returns true, if input character is letter*/
    static boolean checkLetter(char symbol){
         return Pattern.matches("[A-Za-z]", (new Character(symbol)).toString());
    }

    /**Returns true, if input character is numeral*/
    static boolean checkNumeral(char symbol){
        return Pattern.matches("[0-9]",(new Character(symbol)).toString());
    }

    /**Returns true, if input character is punctuation mark*/
    static boolean checkPunctuationMark(char symbol){
        return Pattern.matches("\\.|,|!|\\?|:|;|\"|\'|\\(|\\)", (new Character(symbol)).toString());
    }
}