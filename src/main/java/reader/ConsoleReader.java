package reader;

import writer.AbstractWriter;
import writer.ConsoleWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleReader extends AbstractReader {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static AbstractWriter writer = new ConsoleWriter();

    @Override
    public String readString() {
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public Integer readInt()   {
        Integer amount = null;
        try {
            amount = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            writer.writeLine("Incorrect input\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amount;
    }

}
