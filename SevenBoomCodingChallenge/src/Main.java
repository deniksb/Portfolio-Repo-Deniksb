public class Main {

    public static String sevenBoom(int[] arrayOfNumbers) {

        int i = 0;
        boolean isThereASeven = false;
        while (i < arrayOfNumbers.length) {
            if (arrayOfNumbers[i] == 7) {
                isThereASeven = true;
            }
            i++;
        }
        if (isThereASeven) {
            return "Boom!";
        } else {
            return "There is no seven.";
        }
    }


    public static void main(String[] args) {
        int[] arrayOfNumbers = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] arrayOfNumbersNoSeven = new int[]{1, 2, 3, 4, 5, 6};

        System.out.println(sevenBoom(arrayOfNumbers));
        System.out.println(sevenBoom(arrayOfNumbersNoSeven));

    }
}
