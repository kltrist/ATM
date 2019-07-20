package entity;

import data.AtmDAO;
import data.CreditCardDAO;
import exception.ATMException;
import exception.NotEnoughMoneyException;
import exception.ReplenishLimitException;
import exception.RunOutMoneyInATMException;

import java.sql.SQLException;


public class ATM {

    public static final char CURRENCY = '$';
    private static final int LIMIT = 1_000_000;
    private CreditCardDAO cardDAO;
    private AtmDAO ATMDAO;

    public ATM(CreditCardDAO cardDAO, AtmDAO ATMDAO) {
        this.cardDAO = cardDAO;
        this.ATMDAO = ATMDAO;
    }

    public void pullMoney(CreditCard card, int amount) throws ATMException, SQLException {
        if (amount < 0) throw new IllegalArgumentException("Amount must be positive!");

        if (card.getBalance() < amount)
            throw new NotEnoughMoneyException("Not enough money in your bank account");

        int availableAmount = ATMDAO.getAvailableAmountOfMoney();
        if (availableAmount < amount)
            throw new RunOutMoneyInATMException("Run out of money in ATM");

        card.setBalance(card.getBalance() - amount);
        cardDAO.save(card);

        ATMDAO.setAvailableAmountOfMoney(availableAmount - amount);

    }

    public double checkBalance(CreditCard card) {
        return card.getBalance();
    }

    public void replenishBankAccount(CreditCard card, int amount) throws ATMException, SQLException {
        if (amount < 0) throw new IllegalArgumentException("Amount must be positive!");
        if (amount < LIMIT) {
            double currentBalance = card.getBalance();
            card.setBalance(currentBalance + amount);
            cardDAO.save(card);

            ATMDAO.setAvailableAmountOfMoney(ATMDAO.getAvailableAmountOfMoney() + amount);

        } else throw new ReplenishLimitException("Max amount to replenish is 1 000 000");
    }


}
