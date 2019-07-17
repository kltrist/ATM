package entity;

import exception.ATMException;
import exception.NotEnoughCashException;
import exception.RunOutOfMoneyInATMException;

public class ATM {

    private long availableAmountOfMoney;
    private CreditCard currentCard;

    public void pullMoney(int amount) throws ATMException {
        if (currentCard.getBalance() < amount)
            throw new NotEnoughCashException("Not enough money in your bank account");

        if (availableAmountOfMoney < amount)
            throw new RunOutOfMoneyInATMException("Run out of money in ATM");

        double balance = currentCard.getBalance() - amount;
        currentCard.setBalance(balance);
    }

    public double checkBalance() {
        return currentCard.getBalance();
    }



}
