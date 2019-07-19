
import data.ATMDAO;
import data.ATMDAOImpl;
import data.CreditCardDAO;
import data.CreditCardDAOImpl;
import entity.ATM;
import entity.Authenticator;
import entity.CreditCard;
import exception.WrongPinCodeException;
import org.apache.commons.validator.CreditCardValidator;
import reader.AbstractReader;
import reader.ConsoleReader;
import render.UI;
import writer.AbstractWriter;
import writer.ConsoleWriter;

import java.util.Optional;


public class Main {


    public static void main(String[] args) throws Exception {

        AbstractWriter writer = new ConsoleWriter();
        AbstractReader reader = new ConsoleReader();
        CreditCardDAO cardDAO = new CreditCardDAOImpl();
        ATMDAO atmDAO = new ATMDAOImpl();

        CreditCardValidator validator = new CreditCardValidator();
        Authenticator auth = new Authenticator(cardDAO, validator);

        ATM atm = new ATM(cardDAO, atmDAO);
        UI ui = new UI(reader, writer);


        do {
            writer.writeLine("Welcome!!!\n");
            String cardNumber = ui.userInputRequest("Card number:");
            String pinCode = ui.userInputRequest("Pin code:");

            Optional<CreditCard> cardOptional = auth.tryToLogin(
                    cardNumber.replaceAll("[^0-9]", ""),
                    pinCode);
            CreditCard card = cardOptional.orElseThrow(WrongPinCodeException::new);

            boolean isWork = true;
            while (isWork) {
                ui.showMenu();
                Integer amount;
                String input = reader.readString();
                switch (input) {
                    case "1":
                        writer.writeLine(atm.checkBalance(card));
                        break;
                    case "2":
                        writer.writeLine("How much? ");
                        if ((amount = reader.readInt()) != null) {
                            atm.pullMoney(card, amount);
                            writer.writeLine("\n-" + amount + ATM.CURRENCY);
                        }
                        break;
                    case "3":
                        writer.writeLine("How much? ");
                        if ((amount = reader.readInt()) != null) {
                            atm.replenishBankAccount(card, amount);
                            writer.writeLine("\n+" + amount + ATM.CURRENCY);
                        }
                        break;
                    case "4":
                        isWork = false;
                        writer.writeLine("Good bye!\n");
                        break;
                    default:
                        writer.writeLine("Incorrect input\n");
                }
            }
        } while (!reader.readString().equals("exit"));
    }


}