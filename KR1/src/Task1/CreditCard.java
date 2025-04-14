package Task1;

public class CreditCard extends Card{

    double credit;

    public CreditCard(String owner, double balance, int expireMonth, int expireYear){
        super(owner, balance, expireMonth, expireYear);
    }


    public double getCredit() {
        if (balance>0){
            credit = 0;
        } else if(balance<0){
              credit = 0-balance;
            }
        return credit;
        }

    }

