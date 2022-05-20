import java.util.ArrayList;
import java.util.Random;

public class Program {

    public static void main(String[] args) throws InterruptedException {
        if (!validate(args)){
            System.exit(-1);
        }
        String []size = args[0].split("=");
        int sizeArray = Integer.parseInt(size[1]);
        int []arr = randomArray(sizeArray);

        String []count = args[1].split("=");
        int countTread = Integer.parseInt(count[1]);
        ArrayList<Calculator> calculators = creatCalculators(arr, sizeArray,countTread);

        for (Calculator c:calculators) {
            c.start();
        }
        for (Calculator c:calculators) {
            c.join();
        }
        int sum = 0;

        for (Calculator c:calculators) {
           System.out.println(c.toString());
           sum += c.getSum();
        }
        System.out.println("Sum by threads:"+sum);

    }

    private static ArrayList<Calculator> creatCalculators(int []arr,int sizeArray, int countTread) {
        int start = 0;
        ArrayList<Calculator> calculators = new ArrayList<Calculator>(countTread);
        for (int i =0; i < countTread; i++){
            int end;
            if (start + (sizeArray/countTread) > sizeArray) {
                end = sizeArray - 1;
            }
            else {
                end = start + (sizeArray/countTread);
            }
            calculators.add(new Calculator(arr,start,end));
            start += sizeArray/countTread + 1;
        }
        return calculators;
    }

    private static int[] randomArray(int sizeArray) {
        int []arr;
        arr = new int[(int) sizeArray];
        Random random = new Random();
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    private static boolean validate(String[] args) {
        if (args.length < 2){
            return (false);
        }
        String []count = args[0].split("=");
        String []sizeThread = args[1].split("=");
        if (count.length < 2 || sizeThread.length < 2){
            return (false);
        }
        int IntCount = Integer.parseInt(count[1]);
        int IntSizeTread = Integer.parseInt(sizeThread[1]);
        return (IntCount > 0 && IntCount < 2000000 && IntSizeTread <= IntCount);
    }
}
