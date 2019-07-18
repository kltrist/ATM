package render;

public class ConsoleWriter extends AbstractWriter {

    public void writeLine(String line) {
        System.out.println(line);
    }
}
