package Task1;

public class DebitCard extends Card {

    public DebitCard (String owner, double balance, int expireMonth, int expireYear){
        super(owner, balance, expireMonth, expireYear);
    }



    @Override
    public void withdrawal(double withdrawalAmount) {
        if (withdrawalAmount > limit) {
            throw new IllegalArgumentException("Превышен лимит транзакций по карте");
        } else if (this.balance - withdrawalAmount < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счёте. Вы можете снять не более: " + balance);
        } else {
            this.balance -= withdrawalAmount;
        }
    }
}
