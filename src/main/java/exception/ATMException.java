package exception;

public  abstract class ATMException extends Exception{
    protected String message;

    public ATMException(String message) {
        this.message = message;
    }

    public ATMException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

}
