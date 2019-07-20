package data;

import entity.CreditCard;

import java.sql.SQLException;
import java.util.Optional;

public interface CreditCardDAO {
    Optional<CreditCard> getCardByNumber(String num) throws SQLException;

    CreditCard save(CreditCard card) throws SQLException;

    Optional<String> getPinCodeByCardNumber(String num) throws SQLException;

}
