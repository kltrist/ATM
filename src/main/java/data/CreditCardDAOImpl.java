package data;

import entity.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CreditCardDAOImpl implements CreditCardDAO {


    private final static String SQL_SELECT_CARD = "select * from card_data where card_number=?";
    private final static String SQL_UPDATE_CARD = "update card_data set " +
            "balance=?, attempts_to_login=? where card_number=?";
    private final static String SQL_SELECT_PIN = "select pin_code from credit_cards where card_number=?";
    private PreparedStatement selectCard;
    private PreparedStatement selectPin;
    private PreparedStatement update;


    public CreditCardDAOImpl() {
        Connection con;
        try {
            con = DBCPDataSource.getConnection();
            selectCard = con.prepareStatement(SQL_SELECT_CARD);
            update = con.prepareStatement(SQL_UPDATE_CARD);
            selectPin = con.prepareStatement(SQL_SELECT_PIN);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Optional<CreditCard> getCardByNumber(String num) throws SQLException {
        CreditCard card = null;
        selectCard.setString(1, num);
        ResultSet rs = selectCard.executeQuery();
        rs.next();
        Optional<String> pin = getPinCodeByCardNumber(num);
        if (pin.isPresent()) {
            card = new CreditCard(num,
                    pin.get(),
                    rs.getDouble("balance"),
                    rs.getInt("attempts_to_login"));
        }
        return Optional.ofNullable(card);
    }

    @Override
    public CreditCard save(CreditCard card) throws SQLException {
        update.setDouble(1, card.getBalance());
        update.setInt(2, card.getAttemptsToLogin());
        update.setString(3, card.getNumber());
        update.executeUpdate();
        return card;
    }


    @Override
    public Optional<String> getPinCodeByCardNumber(String num) throws SQLException {
        String pin_code = null;
        selectPin.setString(1, num);
        ResultSet rs = selectPin.executeQuery();
        if (rs.next()) {
            pin_code = rs.getString("pin_code");
        }
        return Optional.ofNullable(pin_code);
    }


}
