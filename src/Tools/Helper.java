package Tools;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;


public class Helper {

    //Scanner
    public final static Scanner scan = new Scanner(System.in);

    //Random input
    public static int getId() {
        Random random = new Random();
        return random.nextInt(Integer.SIZE - 1);
    }

    //Method to format date string input to DateTime format
    public static LocalDateTime StringToDateTimeFormatter(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(input, format);
        return date;
    }

    //Method to format date string input to Date  format
    public static LocalDate StringToDateFormatter(String input) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, format);
        return date;
    }

    //Method to format DateTime input to  string format
    public static String dateToStringFormatter(LocalDateTime input) {

        //                                                        Weekday, Day Month Year Hour : Minutes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH':'mm");
        String date = input.format(formatter);
        return date;
    }

}