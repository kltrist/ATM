package entity;

import data.ATMDAO;
import data.CreditCardDAO;
import exception.ATMException;
import exception.NotEnoughMoneyException;
import exception.ReplenishLimitException;
import exception.RunOutMoneyInATMException;

import java.sql.SQLException;


public class ATM {


    private static final int LIMIT = 1_000_000;
    private CreditCard currentCard;
    private CreditCardDAO cardDAO;
    private ATMDAO ATMDAO;

    public ATM(CreditCard currentCard, CreditCardDAO cardDAO, ATMDAO ATMDAO) {
        this.currentCard = currentCard;
        this.cardDAO = cardDAO;
        this.ATMDAO = ATMDAO;
    }

    public void pullMoney(int amount) throws Exception {
        if (currentCard.getBalance() < amount)
            throw new NotEnoughMoneyException("Not enough money in your bank account");

        int availableAmount = ATMDAO.getAvailableAmountOfMoney();

        if (availableAmount < amount)
            throw new RunOutMoneyInATMException("Run out of money in ATM");

        double balance = currentCard.getBalance() - amount;
        currentCard.setBalance(balance);
        cardDAO.save(currentCard);

        ATMDAO.setAvailableAmountOfMoney(availableAmount - amount);

    }

    public double checkBalance() {
        return currentCard.getBalance();
    }

    public void replenishBankAccount(int amount) throws Exception {
        if (amount < LIMIT) {
            double currentBalance = currentCard.getBalance();
            currentCard.setBalance(currentBalance + amount);
            ATMDAO.setAvailableAmountOfMoney(ATMDAO.getAvailableAmountOfMoney() + amount);
            cardDAO.save(currentCard);
        } else throw new ReplenishLimitException("Max amount to replenish is 1 000 000");
    }


}
