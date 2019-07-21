package exception;

public  abstract class ATMException extends Exception{
    private String message;

    ATMException(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
