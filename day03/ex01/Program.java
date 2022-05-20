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
        Producer pc = new Producer();
        Thread egg = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    pc.egg(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread hen = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    pc.hen(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        egg.start();
        hen.start();
        egg.join();
        hen.join();
    }
}
