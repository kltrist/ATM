package data;

import entity.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardDAOImpl implements CreditCardDAO {

    private Connection con;
    private final static String SQL_SELECT_CARD = "select * from credit_cards where card_number=?";
    private final static String SQL_UPDATE_CARD = "update card_data set card_number=?," +
            "balance=?, attempts_to_login=? where card_number=?";
    private PreparedStatement selectPS;
    private PreparedStatement updatePS;

    public CreditCardDAOImpl(Connection con) throws SQLException {
        this.con = DBCPDataSource.getConnection();
        selectPS = con.prepareStatement(SQL_SELECT_CARD);
        updatePS = con.prepareStatement(SQL_UPDATE_CARD);
    }

    @Override
    public CreditCard getCardByNumber(String num) {
        CreditCard card = null;
        try {
            selectPS.setString(1, num);
            ResultSet rs = selectPS.executeQuery();
            // card = new entity.CreditCard(num,rs.getString("pin_code"),)
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    @Override
    public CreditCard update(CreditCard card) {
        return null;
    }
}
