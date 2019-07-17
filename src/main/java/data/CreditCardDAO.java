package data;

import entity.CreditCard;

public interface CreditCardDAO {
     CreditCard getCardByNumber(String num);
    CreditCard update(CreditCard card);

}
