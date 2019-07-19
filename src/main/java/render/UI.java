package render;

import reader.AbstractReader;
import writer.AbstractWriter;

import java.util.ArrayList;
import java.util.List;

public class UI {

    private AbstractReader reader;
    private AbstractWriter writer;
    private static List<String> menuElements = new ArrayList<>() {{
        add("Check balance");
        add("Pull money");
        add("Replenish");
        add("Exit");

    }};

    public UI(AbstractReader reader, AbstractWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void showMenu() {
        int i = 0;
        writer.writeLine("\n");
        for (String menuElement : menuElements) {
            writer.writeLine((++i)+ "." + menuElement + "\n");
        }

    }

    public  String userInputRequest(String request) {
        writer.writeLine(request);
        return  reader.readString();
    }



}
