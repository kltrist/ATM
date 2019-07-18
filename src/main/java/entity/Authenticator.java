package entity;

import data.CreditCardDAOImpl;
import exception.ATMException;
import exception.BlockedCardException;
import exception.CardNotFoundException;
import exception.InvalidCardNumberException;
import org.apache.commons.validator.CreditCardValidator;
import sun.jvm.hotspot.opto.Block;

import javax.smartcardio.CardNotPresentException;
import java.util.Optional;

public class Authenticator {

    private CreditCardDAOImpl db;
    private CreditCardValidator validator;

    public Authenticator(CreditCardDAOImpl db, CreditCardValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public Optional<CreditCard> tryToLogin(String cardNum, String pin) throws InvalidCardNumberException, CardNotFoundException, BlockedCardException {
        Optional<CreditCard> opt;

        if (!validator.isValid(cardNum)) throw new InvalidCardNumberException();

        Optional<CreditCard> cardOptional = db.getCardByNumber(cardNum);
        if (!cardOptional.isPresent()) throw new CardNotFoundException("There is no such card");

        CreditCard card = cardOptional.get();
        int attempts = card.getAttemptsToLogin();

        if (card.getPassword().equals(pin)) {
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
