package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class CreditCard {

    private String number;
    private String pinCode;
    private double balance;
    private int attemptsToLogin;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char[] digits = number.toCharArray();

        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
            if ((i + 1) % 4 == 0 && (i + 1) != digits.length)
                sb.append('-');
        }
        sb.append("\n")
                .append(pinCode)
                .append("\n")
                .append(balance)
                .append("\n")
                .append(attemptsToLogin)
                .append("\n");
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
                pinCode.equals(that.pinCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pinCode, balance, attemptsToLogin);
    }
}
