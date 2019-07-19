package exception;

public class WrongPinCodeException extends  ATMException {
    public WrongPinCodeException(String message) {
        super(message);
    }

    public WrongPinCodeException() {
    }
}
