public class Program {

    public static void main(String[] args) throws InterruptedException {
        int count;

        if(args.length < 1){
            System.err.println("usage:\\njava Program --count=[int > 0]");
            System.exit(-1);
        }
        count = Integer.parseInt(args[0]);
        if (count < 1){
            System.err.println("usage:\\njava Program --count=[int > 0]");
            System.exit(-1);
        }
        SomeThread egg = new SomeThread(count,"egg");
        SomeThread hen = new SomeThread(count,"hen");
        egg.start();
        hen.start();
        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++) {
            System.out.println("HUMAN");
        }
    }
}
