public class SomeThread extends Thread {
    private int Count;
    private String Name;

    public SomeThread(int count, String name ){
        Count = count;
        Name = name;
    }

    @Override
    public void run() {
        for (int i = 0;i < Count; i++){
            System.out.println(Name);
            try {
                Thread.sleep(100, 0);
            } catch (Exception e) {
                System.err.println("Error in sleep");
                System.exit(-1);
            }
        }
    }
}
