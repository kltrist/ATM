package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMDAOImpl implements ATMDAO {


    private final static String SQL_SELECT_ATM_AMOUNT = "select * from atm_amount top";
    private final static String SQL_UPDATE_ATM_AMOUNT = "update atm_amount set available_amount=? " +
            "where available_amount";
    private PreparedStatement selectAmount;
    private PreparedStatement saveAmount;

    public ATMDAOImpl() throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        selectAmount = con.prepareStatement(SQL_SELECT_ATM_AMOUNT);
        saveAmount = con.prepareStatement(SQL_UPDATE_ATM_AMOUNT);
    }

    @Override
    public int getAvailableAmountOfMoney() {
        int amount = 0;
        try {
            ResultSet rs = selectAmount.executeQuery();
            rs.next();
            amount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    @Override
    public void setAvailableAmountOfMoney(int amount) {

        try {
            saveAmount.setInt(1, amount);
            saveAmount.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
