package data;

import entity.CreditCard;
import writer.ConsoleWriter;

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
    private ConsoleWriter cw = new ConsoleWriter();

    public CreditCardDAOImpl() throws SQLException {
        Connection con = DBCPDataSource.getConnection();

        selectCard = con.prepareStatement(SQL_SELECT_CARD);
        update = con.prepareStatement(SQL_UPDATE_CARD);
        selectPin = con.prepareStatement(SQL_SELECT_PIN);
    }

    @Override
    public Optional<CreditCard> getCardByNumber(String num) {
        CreditCard card = null;
        try {
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
        } catch (SQLException e) {
            cw.writeLine(e.getMessage());
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

    private String test;

    @Override
    public Optional<String> getPinCodeByCardNumber(String num) throws SQLException {
        selectPin.setString(1, num);
        ResultSet rs = selectPin.executeQuery();
        rs.next();
        String pin_code = rs.getString("pin_code");
        return Optional.of(pin_code);
    }


}
