package reader;

import java.io.IOException;

public abstract class AbstractReader {
    public abstract String readString();
    public abstract Integer readInt() throws IOException;
}
