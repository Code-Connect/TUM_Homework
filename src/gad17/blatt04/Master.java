package gad17.blatt04;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Master implements Runnable {
    public static Socket[] storeSockets; //TODO rename after Testing!
    public static HashString hashString; //TODO rename after Testing!

    private Socket client;

    public Master(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        IRequest request;
        try {
            ObjectInputStream clientIn = new ObjectInputStream(client.getInputStream());
            request = (IRequest) clientIn.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Error communicating with client!");
            try {
                client.close();
            } catch (IOException e1) {
            }
            return;
        } catch (IOException e) {
            System.out.println("Error communicating with client!");
            try {
                client.close();
            } catch (IOException e1) {
            }
            return;
        }
        System.out.println("Received: " + request);
        IResponse response = null;

        Socket store = getStore(request); //TODO inline after Testing!

        synchronized (store) {
            try {
                ObjectOutputStream storeOut = new ObjectOutputStream(store.getOutputStream());
                storeOut.writeObject(request);
                storeOut.flush();

                ObjectInputStream storeIn = new ObjectInputStream(store.getInputStream());
                response = (IResponse) storeIn.readObject();
                System.out.println(response);
            } catch (ClassNotFoundException e) {
                System.out.println("Error communicating with store => exiting!");
                System.exit(1);
            } catch (IOException e) {
                System.out.println("Error communicating with store => exiting!");
                System.exit(1);
            }
        }
        try {
            ObjectOutputStream clientOut = new ObjectOutputStream(client.getOutputStream());
            clientOut.writeObject(response);
            clientOut.flush();
        } catch (IOException e) {
            System.out.println("Error communicating with client!");
        } finally {
            try {
                client.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Configuration
        int storeCount = 2;
        int storeServerPort = 5000;
        int clientServerPort = 5555;

        init(storeCount); //TODO inline after Testing!

        ServerSocket storeServer = new ServerSocket(storeServerPort);
        try {
            initStores(storeCount, storeServer); //TODO inline after Testing!
        } finally {
            storeServer.close();
        }
        System.out.println("All storeSockets connected, waiting for client requests.");

        ServerSocket server = new ServerSocket(clientServerPort);
        try {
            while (true) {
                Socket client = server.accept();
                new Thread(new Master(client)).start();
            }
        } finally {
            server.close();
        }
    }

    public static void initStores(int storeCount, ServerSocket serverSocket) {
        for (int i = 0; i < storeCount; i++) {
            System.out.println("Waiting for store " + (i + 1) + " of " + storeCount + "...");
            // TODO: Speichern Sie die akzeptierte Verbindung hier ab.

            // Socket store = storeServer.accept();
            // ...
        }
    }

    public static void init(int storeCount) {
        // *****************************************
        // TODO: Bereiten Sie hier die Sockets und die Hash Funktion f�r die Anzahl der Stores vor.
        // *****************************************
    }

    public static Socket getStore(IRequest request) {
        // *****************************************
        // TODO: Lesen Sie hier den request aus, und entscheiden �ber die Hash Funktion des Keys,
        // welcher Store verwendet werden soll. Beachten Sie hier, dass der selbe Store evtl. von
        // verschiedenen Clients gleichzeitig angefragt werden kann (Stichwort "synchronized").
        // *****************************************
        return null;
    }
}
