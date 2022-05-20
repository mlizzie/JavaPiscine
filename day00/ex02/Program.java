import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (scanner.hasNextLong())
        {
            long number = scanner.nextLong();
            if (number == 42){
                break;
            }
            int summ = 0;
            while (number > 0){
                summ += number % 10;
                number = number / 10;
            }
            if (summ == 1){
                continue;
            }
            boolean isPrime = true;
            for(int i = 2; i*i <= summ; i++)
            {
                if(summ % i == 0){
                    isPrime =false;
                    break;
                }
            }
            if (isPrime){
                count++;
            }
        }
        System.out.println("Count of coffee - request - " + count);
    }
}