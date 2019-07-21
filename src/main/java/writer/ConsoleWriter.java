package writer;

public class ConsoleWriter extends AbstractWriter {

    @Override
    public void writeLine(String obj) {
        System.out.print(obj);
    }

    @Override
    public void writeLine(Object obj) {
        System.out.println(obj.toString());
    }
}
