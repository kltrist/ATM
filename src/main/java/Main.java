
import entity.CreditCard;
import org.apache.commons.validator.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        CreditCard c = new CreditCard("4111111111111111", "123", 2.1);
        CreditCard c2= new CreditCard("41241", "123", 2.1);
        CreditCardValidator ccv = new CreditCardValidator();


    }

}