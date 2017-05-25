package gad17.blatt04;

import java.io.IOException;

/**
 * Diese Klasse implementiert den Visitor des Visitor-Patterns
 * für Requests.
 */
public class RequestVisitor {
    /**
     * Der Callback, der für {@link ReadRequest}-Objekte aufgerufen wird.
     */
    private IOExceptionConsumer<ReadRequest> readCallback;

    /**
     * Der Callback, der für {@link StoreRequest}-Objekte aufgerufen wird.
     */
    private IOExceptionConsumer<StoreRequest> storeCallback;

    public void __(IOExceptionConsumer<ReadRequest> readCallback, IOExceptionConsumer<StoreRequest> storeCallback) {
        this.readCallback = readCallback;
        this.storeCallback = storeCallback;
    }

    public void visit(ReadRequest rr) throws IOException {
        readCallback.accept(rr);
    }

    public void visit(StoreRequest sr) throws IOException {
        storeCallback.accept(sr);
    }
}
