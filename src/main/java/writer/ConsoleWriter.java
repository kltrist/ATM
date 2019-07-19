package writer;

public class ConsoleWriter extends AbstractWriter {

    @Override
    public void writeLine(Object obj) {
        System.out.print(obj.toString());
    }
}
