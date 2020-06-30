import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a string to check if it is a Hex.");
        String hex = reader.nextLine();
        char[] hexArray = hex.toCharArray();
        char hashtag = "#".toCharArray()[0];

        //checks if it is empty
        if (hex.isEmpty()) {
            System.out.println("NOT A HEX");
            System.exit(0);
        }
        //checks if it starts with hashtag
        if (hexArray[0] == hashtag) {
            int i = 1;
            //iterates through it to check if it only contains the allowed characters
            while (i < hex.length()) {
                String buffer = "" + hexArray[i];
                if (buffer.matches("[0123456789abcdefABCDEF]")) {
                    i++;
                    System.out.println("ITS A HEX");
                } else {
                    System.out.println("NOT A HEX");
                    break;
                }

            }
        } else {
            System.out.println("NOT A HEX");
        }


    }
}
