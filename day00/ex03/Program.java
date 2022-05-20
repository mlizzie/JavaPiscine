import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int digits = 0;
        while(scanner.hasNext())
        {
            String string = scanner.next();
            count++;

            if (string.equals(("42"))){
                break;
            }
            if (!string.equalsIgnoreCase("Week")) {
                System.err.println("Illegal Argument");
                System.exit(1);
            }
            if(!scanner.hasNextInt() || count != scanner.nextInt() || count > 18){
                System.err.println("Illegal Argument");
                System.exit(1);
            }
            if(!"".equals(scanner.nextLine())){
                System.err.println("Illegal Argument");
                System.exit(1);
            }

            int min = 9;
            int num ;
            for(int i = 0;i < 5; i++){
                if (!scanner.hasNextInt()){
                    System.err.println("Illegal Argument");
                    System.exit(1);
                }
                num = scanner.nextInt();
                if(num > 9 || num < 0){
                    System.err.println("Illegal Argument");
                    System.exit(1);
                }
                if (num < min){
                    min = num;
                }
            }
            for(int i = 1; i < count; i++){
                min *= 10;
            }
            digits += min;
        }

        int i = 1;
        while (digits != 0) {
           System.out.print("week " + i++ + " ");
           int digit = digits % 10;
           digits /= 10;
           while (digit != 0){
               System.out.print("=");
               digit--;
           }
           System.out.println(">");
        }
    }
}