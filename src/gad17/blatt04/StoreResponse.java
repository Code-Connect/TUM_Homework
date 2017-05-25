package gad17.blatt04;

import java.io.IOException;

/**
 * Die {@link StoreResponse} signalisiert Erfolg bei der
 * Durchführung des {@link StoreRequest}s.
 */
public class StoreResponse implements IResponse {
    private static final long serialVersionUID = 1L;

    public StoreResponse() {
    }

    @Override
    public String toString() {
        return "Store() success";
    }

    @Override
    public void accept(ResponseVisitor v) throws IOException {
        v.visit(this);
    }
}
