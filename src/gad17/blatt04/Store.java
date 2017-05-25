package gad17.blatt04;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class Store {

    public static Map data; //TODO rename after Testing!

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Configuration
        String masterHost = "127.0.0.1";
        int masterStorePort = 5000;

        init();

        Socket master = new Socket(masterHost, masterStorePort);
        try {
            while (true) {
                ObjectInputStream masterIn = new ObjectInputStream(master.getInputStream());
                ObjectOutputStream masterOut = new ObjectOutputStream(master.getOutputStream());
                IRequest request = (IRequest) masterIn.readObject();
                System.out.println(request);
                RequestVisitor rv = new RequestVisitor();
                Mutable<IResponse> response = new Mutable<IResponse>();
                rv.__((ReadRequest readRequest) -> {
                    ReadResponse readResponse = onReadRequest(readRequest);
                    response.set(readResponse);

                }, (StoreRequest storeRequest) -> {
                    onStoreRequest(storeRequest);
                    response.set(new StoreResponse());
                });
                request.accept(rv);
                System.out.println(response.get());
                masterOut.writeObject(response.get());
                masterOut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            master.close();
        }
    }

    public static void init() {
        // *****************************************
        // TODO: Bereiten Sie eine Struktur vor, um die �bergebenen und angefragten Werte zu speichern.
        // *****************************************
    }

    public static void onStoreRequest(StoreRequest storeRequest) {
        // *****************************************
        // TODO: Speichern Sie den �bergebenen Wert.
        // *****************************************
    }

    public static ReadResponse onReadRequest(ReadRequest readRequest) {
        // *****************************************
        // TODO: Lesen Sie den angefragten Wert aus (sofern vorhanden!),
        // bereiten Sie eine passende ReadResponse zur Anfrage vor,
        // und �bergeben Sie sie an das response Objekt.
        // *****************************************
        return null;
    }
}
