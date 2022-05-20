import java.util.UUID;

class TransactionNotFoundException extends RuntimeException {}

public class TransactionsLinkedList implements TransactionsList {

    private  Node Start;
    private  Node End;
    private  Integer count = 0;

    @Override
    public void add(Transaction transaction) {
        if (Start != null){
            Node node = new Node(transaction);
            End = node.add(End);
        }else {
            Node node = new Node(transaction);
            Start = node;
            End = node;
        }
        count++;
    }

    @Override
    public void remove(UUID uuid) {

            Node node = Start;
            while (node != null){
                if (node.getTransaction().getIdentifier() == uuid){
                    if (node == Start){
                        Start = Start.next;
                        Start.prev = null;
                    }else{
                        Start.remove(node);
                    }
                    count--;
                    return;
                }
                node = node.next;
            }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction [] transactions = new Transaction[count];
        Node node = Start;
        for (int i = 0; i < transactions.length; i++){
            transactions[i] = node.getTransaction();
            node = node.next;
        }
        return transactions;

    }
    private class Node{
        private Node   next;
        private  Node  prev;
        private Transaction transaction;


        public Node(Transaction transaction){
            this.transaction = transaction;
        }

        public Node add(Node node){

            node.next = this;
            this.prev = node;
            this.next = null;
            return  this;
        }
        public  void remove(Node node){

            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            if (next != null){
                next.prev = prev;
            }
        }

        public Transaction getTransaction() {
            return transaction;
        }

    }
}
