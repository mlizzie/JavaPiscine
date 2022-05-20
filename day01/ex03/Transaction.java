import java.util.UUID;

enum Category{
    debits,
    credits
}

public class Transaction {

    private UUID Identifier;
    private User Recipient;
    private User Sender;
    private Category TransferCategory;
    private Integer TransferAmount;
    public Transaction(User recipient, User sender, Category category, Integer transferAmount){

        Recipient = recipient;
        Sender = sender;
        Identifier = UUID.randomUUID();
        TransferCategory = category;
        TransferAmount = transferAmount;
        if (TransferCategory == Category.debits && transferAmount < 0) {
            System.out.println("Debit (incoming transaction) can't be negative, set to 0");
            TransferAmount = 0;
        } else if (TransferCategory == Category.credits && transferAmount > 0) {
            System.out.println("Credit (outgoing transaction) can't be positive, set to 0");
            TransferAmount = 0;
        }
    }

    public User getRecipient() {
        return Recipient;
    }

    public User getSender() {
        return Sender;
    }

    public UUID getIdentifier() {
        return Identifier;
    }

    public Category getTransferCategory() {
        return TransferCategory;
    }

    public Integer getTransferAmount() {
        return TransferAmount;
    }

    public void setIdentifier(UUID identifier) {
        Identifier = identifier;
    }

    public void setRecipient(User recipient) {
        Recipient = recipient;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public void setTransferCategory(Category transferCategory) {
        TransferCategory = transferCategory;
    }

    public void setTransferAmount(Integer transferAmount) {
        TransferAmount = transferAmount;
        if (TransferCategory == Category.debits && transferAmount < 0) {
            System.out.println("Debit (incoming transaction) can't be negative, set to 0");
            TransferAmount = 0;
        } else if (TransferCategory == Category.credits && transferAmount > 0) {
            System.out.println("Credit (outgoing transaction) can't be positive, set to 0");
            TransferAmount = 0;
        }
    }

    @Override
    public String toString() {
        return ("Transaction: " + TransferCategory + " sender-" + getSender().getName() + " Recipient-" + getRecipient().getName()) + "\n";
    }
}
