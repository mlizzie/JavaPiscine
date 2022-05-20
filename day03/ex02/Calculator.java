public class Calculator extends Thread{
    private  int []Arr;
    private  int Start;
    private  int End;
    private  int Sum;
    private static int id = 1;

    Calculator(int []arr, int start, int end) {
        Arr = arr;
        Start = start;
        End = end;
        id++;
    }

    @Override
    public void run() {
        this.sum();
    }

    private void sum() {
        for(int i = Start; i <= End; i++){
            Sum += Arr[i];
        }
    }

    public int getSum() {
        return Sum;
    }

    @Override
    public String toString() {
        return ("Thread " + id + ": from " + Start + " to " + End + " sum is " + Sum);
    }
}
