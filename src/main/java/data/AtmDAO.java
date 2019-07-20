package data;

import java.sql.SQLException;

public interface AtmDAO {
    int getAvailableAmountOfMoney() throws SQLException;
    void setAvailableAmountOfMoney(int amount) throws SQLException;
}
