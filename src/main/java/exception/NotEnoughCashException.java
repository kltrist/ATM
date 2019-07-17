package exception;

public class NotEnoughCashException extends ATMException {


    public NotEnoughCashException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
