import java.util.ArrayList;
import java.util.Arrays;


public class Main {


    public static String[] identicalFilter(String[] arr) {
        int i = 0;

        ArrayList<String> listToConvert = new ArrayList<>();
        String pattern = "(.)\\1*\\b";

        while (i < arr.length) {

            if (arr[i].matches(pattern)) {
                listToConvert.add(arr[i]);
            }
            i++;
        }
        String[] arrayToReturn = new String[listToConvert.size()];
        listToConvert.toArray(arrayToReturn);
        return arrayToReturn;

    }


    public static void main(String[] args) {
        String[] arrayToTest = new String[]{"xxxxo", "oxo", "xox", "ooxxoo", "oxo"};
        System.out.println(Arrays.toString(identicalFilter(arrayToTest)));


    }
}
