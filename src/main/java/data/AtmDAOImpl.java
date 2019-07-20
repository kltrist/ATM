package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtmDAOImpl implements AtmDAO {


    private final static String SQL_SELECT_ATM_AMOUNT = "select * from atm_amount top";
    private final static String SQL_UPDATE_ATM_AMOUNT = "update atm_amount set available_amount=? " +
            "where available_amount";
    private PreparedStatement selectAmount;
    private PreparedStatement saveAmount;

    public AtmDAOImpl() {
        Connection con;
        try {
            con = DBCPDataSource.getConnection();
            selectAmount = con.prepareStatement(SQL_SELECT_ATM_AMOUNT);
            saveAmount = con.prepareStatement(SQL_UPDATE_ATM_AMOUNT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getAvailableAmountOfMoney() throws SQLException {
        ResultSet rs = selectAmount.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public void setAvailableAmountOfMoney(int amount) throws SQLException {
            saveAmount.setInt(1, amount);
            saveAmount.executeUpdate();
    }
}
