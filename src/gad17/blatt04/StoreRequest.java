package gad17.blatt04;

import java.io.IOException;

public class StoreRequest implements IRequest {
    private static final long serialVersionUID = 1L;

    private String key;

    @Override
    public String getKey() {
        return key;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public StoreRequest(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Store(" + key + " <- " + value + ")";
    }

    @Override
    public void accept(RequestVisitor v) throws IOException {
        v.visit(this);
    }
}
