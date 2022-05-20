import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class Program {

    private  static HashSet<String>  dictionary = new HashSet<String>();
    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("need 2 files");
            System.exit(1);
        }
        try {
            readFileInDictionary(args[0]);
            readFileInDictionary(args[1]);

            int []v1;
            int []v2;
            v1 = getVector(args[0]);
            v2 = getVector(args[1]);
            double similarity = multy(v1, v2) / (vectorLen(v1) * vectorLen(v2));
            System.out.printf("Similarity = %.2f", similarity);
            writeDictionaryInFile();
        }catch (Exception e){
           System.err.println(e.getMessage());
           System.exit(1);
        }
    }

    private static void writeDictionaryInFile() {
        try  (BufferedWriter  bufferedWriter = new BufferedWriter(new FileWriter("dictionary.txt"))){
                for (String s : dictionary) {
                    bufferedWriter.write(s + "\n");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] getVector(String path) throws IOException {
         int []v = new int[dictionary.size()];
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                for (String s : br.readLine().trim().split("\\s+")) {
                    if (s.isEmpty()) {
                        continue;
                    }
                    Iterator<String> it = dictionary.iterator();
                    int i = 0;
                    while (it.hasNext()){
                        if(s.equals(it.next())){
                            v[i]++;
                        }
                        i++;
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Can't read file " + path);
        }

         return v;
    }

    private static void readFileInDictionary(String path) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                for (String s : br.readLine().trim().split("\\s+")) {
                    if (s.isEmpty()) {
                        continue;
                    }
                    dictionary.add(s);
                }
            }
        } catch (IOException e) {
            throw new IOException("Can't read file " + path);
        }
    }
    private static double multy(int v1[], int v2[]) {
        double answer = 0;
        for (int i = 0; i < v1.length; i++) {
            answer += v1[i] * v2[i];
        }
        return answer;
    }

    private static double vectorLen(int v[]) {
        double len = 0;
        for (int i = 0; i < v.length; i++) {
            len += (double)v[i] * v[i];
        }
        return Math.sqrt(len);
    }

}
