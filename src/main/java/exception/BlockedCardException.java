package exception;

public class BlockedCardException extends ATMException {

    public BlockedCardException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
