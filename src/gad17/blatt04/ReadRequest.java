package gad17.blatt04;

import java.io.IOException;

public class ReadRequest implements IRequest {
    private static final long serialVersionUID = 1L;

    private String key;

    @Override
    public String getKey() {
        return key;
    }

    public ReadRequest(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Read(" + key + ")";
    }

    @Override
    public void accept(RequestVisitor v) throws IOException {
        v.visit(this);
    }
}
