import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
     static  private HashMap<String, String> signatures = new HashMap<String, String>();
     static  private ArrayList<String> Buf  = new ArrayList<String>();
     private static final String litHexCodes = "0123456789ABCDEF";

    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("signatures.txt");
            loadSignatures(inputStream);
            getUserSelectedFile();
            System.out.println("PROCESSED");
            writeResult();
            inputStream.close();
            Buf.clear();
            signatures.clear();
        }
        catch(Exception e) {
        System.err.println("Can't open result.txt/signatures.txt files");
        System.exit(-1);
    }
    }

    private static void writeResult() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("result.txt");
            for (String b : Buf) {
                for (Map.Entry<String, String> pair : signatures.entrySet()) {
                    if (b.lastIndexOf(pair.getValue()) != -1) {
                        fileOutputStream.write(pair.getKey().getBytes());
                        fileOutputStream.write('\n');
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getUserSelectedFile() {
        String requestFile = "";
        try {
            Scanner scanner = new Scanner(System.in);
            byte [] significationInFile = new byte[7];
            while (scanner.hasNextLine()){
                requestFile = scanner.nextLine();
                if (requestFile.equals("42")){
                    return;
                }
                FileInputStream fileInputStream = new FileInputStream(requestFile);
                fileInputStream.read(significationInFile);
                fileInputStream.close();
                Buf.add(getHex(significationInFile));
            }

        }catch (Exception e){
            System.err.println("Can't read file " + requestFile);
        }
    }

    public static String getHex(byte[] inputBytes) {
        final StringBuilder hex = new StringBuilder(2 * inputBytes.length);
        for (final byte b : inputBytes) {
            hex.append(litHexCodes.charAt((b & 0xF0) >> 4)).append(litHexCodes.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    private static void loadSignatures(FileInputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        String lineOfSignature;
        String extension;
        String signatureOfExtension;

        while (scanner.hasNextLine()){
            lineOfSignature = scanner.nextLine();
            extension = lineOfSignature.substring(0, lineOfSignature.indexOf(','));
            signatureOfExtension = lineOfSignature.substring(lineOfSignature.indexOf(',') + 1);
            signatureOfExtension = signatureOfExtension.replaceAll("\\s", "");
            signatures.put(extension, signatureOfExtension);
        }
    }
}
