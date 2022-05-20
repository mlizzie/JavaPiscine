public class User {

    private Integer Identifier;
    private String Name;
    private Integer Balance;
    private TransactionsList transactionsList;

    public User(String name, Integer balance){

        Identifier = UserIdsGenerator.getInstance().generateId();
        Name = name;
        if (balance > 0){
            Balance = balance;
        }
        else{
            Balance = 0;
        }
    }

    public Integer getIdentifier() {
        return Identifier;
    }

    public Integer getBalance() {
        return Balance;
    }

    public String getName() {
        return Name;
    }

    public void setIdentifier(Integer identifier) {
        Identifier = identifier;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBalance(Integer balance) {
        if (balance > 0) {
            Balance = balance;
        }else {
            Balance = 0;
        }
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(TransactionsList transactionsList) {
        this.transactionsList = transactionsList;
    }
}
