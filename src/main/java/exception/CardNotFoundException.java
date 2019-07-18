package exception;

public class CardNotFoundException extends ATMException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
