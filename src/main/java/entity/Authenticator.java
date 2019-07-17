package entity;

import data.CreditCardDAOImpl;
import exception.ATMException;
import exception.BlockedCardException;
import exception.InvalidCardNumberException;
import org.apache.commons.validator.CreditCardValidator;

import java.util.Optional;

public class Authenticator {

    private CreditCardDAOImpl db;
    private CreditCardValidator validator;

    public Authenticator(CreditCardDAOImpl db, CreditCardValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public Optional<CreditCard> tryToLogin(String cardNum, String pin) throws ATMException {
        Optional<CreditCard> opt;

        if (validator.isValid(cardNum)) {
            CreditCard card = db.getCardByNumber(cardNum);
            int attempts = card.getAttemptsToLogin();

            if (card.getPassword().equals(pin)) {
                if (attempts > 0) {
                    card.setAttemptsToLogin(3);
                    opt = new Optional<>(card);
                } else
                    throw new BlockedCardException("Card was blocked");
            } else {
                card.setAttemptsToLogin(--attempts);
                opt = new Optional<>();
            }
        } else throw new InvalidCardNumberException("Invalid card number");
        return opt;
    }
}
