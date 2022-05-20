import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int count = 0;

        if (!scanner.hasNextLine()) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        char arr[] = scanner.nextLine().toCharArray();
        if (arr.length == 0) {
            System.exit(0);
        }

        int map[] = new int[255];
        for (char n:arr){
            map[n]++;
        }

        for (int i = 0;i < map.length; i++) {
            if (map[i] > 0){
                count++;
                if (map[i] > 999){
                    System.err.println("Illegal Argument");
                    System.exit(-1);
                }
            }
        }

        int size = 0;
        int maxIndex = 0;
        int indexs[] = new int[10];

        System.out.println();
        while (size < 10 && size < count)
        {
            int max = 0;
            for (int i = 0; i < map.length; i++){
                if (map[i] > max){
                    max = map[i];
                    maxIndex = i;
                    indexs[size] = i;
                }
            }
            int n = size;
            while (n > 0){
                n--;
                System.out.print("# ");
            }
            System.out.println(map[maxIndex]);
            map[maxIndex] = 0;
            size++;
        }
        for (int i = 0; i < size; i++) {
            System.out.printf("%c ", (char) indexs[i]);
        }

    }
}
