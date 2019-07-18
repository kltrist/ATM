package exception;

public class InvalidCardNumberException extends  ATMException {
    public InvalidCardNumberException() {
    }

    public InvalidCardNumberException(String message) {
        super(message);
    }
}
