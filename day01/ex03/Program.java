public class Program {

    public static void main(String[] args) {


        User user1 = new User("Паша", 10_000);
        User user2 = new User("Миша", 100_000);
        User user3 = new User("Сережа", 200_000);

        TransactionsList list = new TransactionsLinkedList();
        user1.setTransactionsList(list);
        user2.setTransactionsList(list);
        user3.setTransactionsList(list);


        Transaction transaction1 = new Transaction(user2, user1, Category.credits, -500);
        Transaction transaction2 = new Transaction(user1, user2,  Category.debits, 500);
        Transaction transaction3 = new Transaction(user2, user3,  Category.credits, -500);
        Transaction transaction4 = new Transaction(user3, user2, Category.debits, 500);


        list.add(transaction1);
        list.add(transaction2);
        list.add(transaction3);
        list.add(transaction4);
        Transaction []transactions = list.toArray();
        for (int i = 0; i < transactions.length; i++){
         System.out.println(transactions[i].toString());
        }
        System.out.println("--------------------------------------------------");
        list.remove(transaction2.getIdentifier());
        transactions = list.toArray();
        for (int i = 0; i < transactions.length; i++){
            System.out.println(transactions[i].toString());
        }
        System.out.println("--------------------------------------------------");
        list.remove(transaction1.getIdentifier());
        transactions = list.toArray();
        for (int i = 0; i < transactions.length; i++){
            System.out.println(transactions[i].toString());
        }
    }
}