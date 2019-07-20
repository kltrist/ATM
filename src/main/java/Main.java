import data.AtmDAO;
import data.AtmDAOImpl;
import data.CreditCardDAO;
import data.CreditCardDAOImpl;
import entity.ATM;
import entity.Authenticator;
import entity.CreditCard;
import exception.ATMException;
import org.apache.commons.validator.CreditCardValidator;
import reader.AbstractReader;
import reader.ConsoleReader;
import ui.UI;
import writer.AbstractWriter;
import writer.ConsoleWriter;

import java.sql.SQLException;
import java.util.Optional;


public class Main {
    private static AbstractWriter writer = new ConsoleWriter();
    private static AbstractReader reader = new ConsoleReader();

    private static CreditCardDAO cardDAO = new CreditCardDAOImpl();
    private static AtmDAO atmDAO = new AtmDAOImpl();

    private static CreditCardValidator validator = new CreditCardValidator();
    private static Authenticator auth = new Authenticator(cardDAO, validator);
    private static ATM atm = new ATM(cardDAO, atmDAO);
    private static UI ui = new UI(reader, writer);

    public static void main(String[] args) {

        do {
            writer.writeLine("Welcome!!!\n");
            String cardNumber = ui.userInputRequest("Card number:")
                    .replaceAll("[^0-9]", "");
            String pinCode = ui.userInputRequest("Pin code:");
            try {
                Optional<CreditCard> cardOptional = auth.tryToLogin(cardNumber, pinCode);
                CreditCard card = cardOptional.orElseThrow(
                        () -> new IllegalArgumentException("Wrong pin-code!")
                );

                boolean isWork = true;
                while (isWork) {
                    ui.showMenu();
                    String input = reader.readString();
                    switch (input) {
                        case "1":
                            writer.writeLine(atm.checkBalance(card));
                            break;
                        case "2":
                            pullMoneyHandler(card);
                            break;
                        case "3":
                            replenishBankAccountHandler(card);
                            break;
                        case "4":
                            isWork = false;
                            writer.writeLine("Good bye!\n");
                            break;
                        default:
                            writer.writeLine("Incorrect input\n");
                    }
                }
            } catch (ATMException | IllegalArgumentException e) {
                writer.writeLine(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!ui.userInputRequest("\nPress any key to continue... or write exit\n")
                .equals("exit"));
    }

    private static void pullMoneyHandler(CreditCard card) throws ATMException, SQLException {
        Integer amount;
        writer.writeLine("How much? ");
        if ((amount = reader.readInt()) != null) {
            atm.pullMoney(card, amount);
            writer.writeLine("\n-" + amount + ATM.CURRENCY);
        }

    }

    private static void replenishBankAccountHandler(CreditCard card) throws ATMException, SQLException {
        Integer amount;
        writer.writeLine("How much? ");
        if ((amount = reader.readInt()) != null) {
            atm.replenishBankAccount(card, amount);
            writer.writeLine("\n+" + amount + ATM.CURRENCY);
        }
    }


}