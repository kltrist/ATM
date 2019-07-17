package exception;

public class InvalidCardNumberException extends ATMException {

    public InvalidCardNumberException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
