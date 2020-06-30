import java.time.LocalDate;
import java.util.Date;

public class Main {

    //method to check if the given date is a magic year
    public static boolean isItAMagicYear(int year, int month, int day) {

        boolean forReturn = false;
        //some parsing from integer to string so we can count numbers/ other variables
        String yearString = Integer.toString(year);
        String lastDigit = "" + yearString.charAt(3);
        String lastTwoDigits = yearString.charAt(2) + lastDigit;
        String lastThreeDigits = yearString.charAt(1) + lastTwoDigits;
        int product = month * day;
        String productString = Integer.toString(product);

        if (productString.length() == 1) {
            if (Integer.parseInt(lastDigit) == product) {
                forReturn = true;
            }
        } else if (productString.length() == 2) {
            if (Integer.parseInt(lastTwoDigits) == product) {
                forReturn = true;
            }
        } else if (productString.length() == 3) {
            if (Integer.parseInt(lastThreeDigits) == product) {
                forReturn = true;
            }
        }


        return forReturn;
    }


    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2010, 2, 5);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        boolean itAMagicYear = Main.isItAMagicYear(year, month, day);
        if (itAMagicYear == true) {
            System.out.println("Its a magic date!");
        } else {
            System.out.println("Its not a magic date...");
        }


    }
}
