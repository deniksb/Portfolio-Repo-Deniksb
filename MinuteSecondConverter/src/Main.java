public class Main {

    public static String convert( int[] arr) {
        String toBeReturned = "there is no 7 in the array";
      int i = 0;
      while (i < arr.length){
          if(Integer.toString(arr[i]).contains("7")){
            toBeReturned = "Boom!";
          }


          i++;
      }
      return toBeReturned;

    }


    public static void main(String[] args) {

    }
}
