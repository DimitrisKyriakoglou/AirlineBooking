package Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {

    //method to Validate if the DateTime entered is in valid format
    public static String validateDateTime(String input) {
        String temp;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            format.parse(input);
            return input;
        } catch (ParseException e) {
            String newInput;
            System.out.println(
                    "You've Entered an  invalid date and time format please Enter the date and time in this format yyyy-MM-dd HH:mm ");
            System.out.print("");
            newInput = Helper.scan.nextLine();
            temp = validateDateTime(newInput);
        }
        return temp;
    }

    //method to Validate if the Date entered is in valid format
    public static String validateDate(String input) {
        String temp;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(input);
            return input;
        } catch (ParseException e) {
            String newInput;
            System.out.println(
                    "You've Entered an  invalid date and time format please Enter the date and time in this format yyyy-MM-dd");
            System.out.print("");
            newInput = Helper.scan.nextLine();
            temp = validateDate(newInput);
        }
        return temp;
    }

    //empty input
    public static boolean intIsEmpty(int input) {
        int number = -1;
        number = input;
        return number == -1;
    }


}
