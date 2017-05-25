package gad17.blatt04;

import java.io.IOException;

/**
 * Diese Klasse implementiert den Visitor des Visitor-Patterns
 * für Responses.
 */
public class ResponseVisitor {
    /**
     * Der Callback, der für {@link ReadResponse}-Objekte aufgerufen wird.
     */
    private IOExceptionConsumer<ReadResponse> readCallback;

    /**
     * Der Callback, der für {@link StoreResponse}-Objekte aufgerufen wird.
     */
    private IOExceptionConsumer<StoreResponse> storeCallback;

    public void __(IOExceptionConsumer<ReadResponse> readCallback, IOExceptionConsumer<StoreResponse> storeCallback) {
        this.readCallback = readCallback;
        this.storeCallback = storeCallback;
    }

    public void visit(ReadResponse rr) throws IOException {
        readCallback.accept(rr);
    }

    public void visit(StoreResponse sr) throws IOException {
        storeCallback.accept(sr);
    }
}
