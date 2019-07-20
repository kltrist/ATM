package entity;

import data.CreditCardDAO;
import exception.ATMException;
import exception.BlockedCardException;
import exception.CardNotFoundException;

import org.apache.commons.validator.CreditCardValidator;

import java.sql.SQLException;
import java.util.Optional;

public class Authenticator {

    private CreditCardDAO cardDAO;
    private CreditCardValidator validator;

    public Authenticator(CreditCardDAO cardDAO, CreditCardValidator validator) {
        this.cardDAO = cardDAO;
        this.validator = validator;
    }

    public Optional<CreditCard> tryToLogin(String cardNum, String pin) throws ATMException, SQLException {
        Optional<CreditCard> opt;

        if (!validator.isValid(cardNum)) throw new IllegalArgumentException("Card number isn't valid");

        Optional<CreditCard> cardOptional = cardDAO.getCardByNumber(cardNum);
        if (cardOptional.isEmpty()) throw new CardNotFoundException("There is no such card");

        CreditCard card = cardOptional.get();
        int attempts = card.getAttemptsToLogin();
        if (attempts == 0) throw new BlockedCardException("Card was blocked");

        if (card.getPinCode().equals(pin) && attempts > 0) {
            card.setAttemptsToLogin(3);
            opt = cardOptional;
        } else {
            card.setAttemptsToLogin(--attempts);
            opt = Optional.empty();
        }
        cardDAO.save(card);
        return opt;
    }
}
