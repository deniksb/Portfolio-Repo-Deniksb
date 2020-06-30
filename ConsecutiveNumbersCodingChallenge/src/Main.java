import java.util.Arrays;

public class Main {


    //checks if list can be rearranged to be consecutive numbers with no repeats
    public static boolean canTheyBeRearranged(int[] listOfNumbers) {
        boolean toBeReturned = false;


        int i = 1;
        int j = listOfNumbers[0];
        Arrays.sort(listOfNumbers);
        while (i < listOfNumbers.length) {
            if (listOfNumbers[i] == listOfNumbers[i - 1] + 1) {
                toBeReturned = true;
            } else {
                toBeReturned = false;
                break;
            }

            i++;
        }

        return toBeReturned;

    }


    public static void main(String[] args) {
        int[] listOfNumbers = new int[]{3, 2, 1, 4, 5};
        int[] listOfNumbers2 = new int[]{5, 1, 4, 3, 2, 8};
        int[] listOfNumbers3 = new int[]{5, 6, 7, 8, 9, 9};
        System.out.println(canTheyBeRearranged(listOfNumbers)); //true
        System.out.println(canTheyBeRearranged(listOfNumbers2)); //false
        System.out.println(canTheyBeRearranged(listOfNumbers3)); //false
    }
}
