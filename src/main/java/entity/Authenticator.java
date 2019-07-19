package entity;

import data.CreditCardDAO;
import exception.BlockedCardException;
import exception.CardNotFoundException;
import exception.InvalidCardNumberException;
import org.apache.commons.validator.CreditCardValidator;
import java.util.Optional;

public class Authenticator {

    private CreditCardDAO db;
    private CreditCardValidator validator;

    public Authenticator(CreditCardDAO db, CreditCardValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public Optional<CreditCard> tryToLogin(String cardNum, String pin) throws Exception {
        Optional<CreditCard> opt;

        if (!validator.isValid(cardNum)) throw new InvalidCardNumberException();

        Optional<CreditCard> cardOptional = db.getCardByNumber(cardNum);
        if (cardOptional.isEmpty()) throw new CardNotFoundException("There is no such card");

        CreditCard card = cardOptional.get();
        int attempts = card.getAttemptsToLogin();

        if (card.getPinCode().equals(pin)) {
            if (attempts > 0) {
                card.setAttemptsToLogin(3);
                opt = cardOptional;
            } else
                throw new BlockedCardException( "Card was blocked");
        } else {
            card.setAttemptsToLogin(--attempts);
            opt = Optional.empty();
        }

        return opt;
    }
}
