public class Producer {

    enum  enumT{
        hen,
        egg
    }

    enumT en = enumT.hen;

    public void hen(int count) throws  InterruptedException{
        for (int i = 0; i < count; i++){
            synchronized (this){
                if (en == enumT.hen){
                    wait();
                }
                System.out.println("hen");
                en = enumT.hen;
                notify();
                Thread.sleep(300);
            }
        }
    }

    public void egg(int count)throws  InterruptedException {
        for (int i = 0; i < count; i++){
            synchronized (this){
                if (en == enumT.egg){
                    wait();
                }
                System.out.println("egg");
                en = enumT.egg;
                notify();
                Thread.sleep(300);
            }
        }
    }
}
