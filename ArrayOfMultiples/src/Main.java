import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static int[] arrayOfMultiples(int num, int length) {
        ArrayList<Integer> listOfIntegers = new ArrayList<>();


    int l = length;
    while(l>0){
        listOfIntegers.add(l*num);
        l--;
    }


        int[] arrayToReturn = listOfIntegers.stream().mapToInt(i -> i).toArray();
        Arrays.sort(arrayToReturn);
        return arrayToReturn;

    }

    public static void main(String[] args) {


    }
}
