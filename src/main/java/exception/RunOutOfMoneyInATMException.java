package exception;

public class RunOutOfMoneyInATMException extends ATMException {


    public RunOutOfMoneyInATMException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
