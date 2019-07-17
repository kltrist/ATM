package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class CreditCard {

    private String number;
    private String password;
    private double balance;
    private int attemptsToLogin;

    public CreditCard(String number, String password, double balance) {
        this.number = number;
        this.password = password;
        this.balance = balance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char[] digits = number.toCharArray();

        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
            if ((i + 1) % 4 == 0 && (i + 1) != digits.length)
                sb.append('-');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return Double.compare(that.balance, balance) == 0 &&
                attemptsToLogin == that.attemptsToLogin &&
                number.equals(that.number) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, password, balance, attemptsToLogin);
    }
}
