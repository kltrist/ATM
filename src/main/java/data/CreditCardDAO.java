package data;

import entity.CreditCard;

import java.util.Optional;

public interface CreditCardDAO {
    Optional<CreditCard> getCardByNumber(String num) throws Exception;

    CreditCard save(CreditCard card) throws Exception;

    Optional<String> getPinCodeByCardNumber(String num) throws Exception;

}
