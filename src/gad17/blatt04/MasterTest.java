package gad17.blatt04;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MasterTest {

    private Socket getStore(IRequest request) {
        return Master.getStore(request);
    }

    private void runInit() {
        Master.init(2);
    }

    private void runInitStores() throws IOException {
        Master.initStores(2, new ServerSocket() {
            @Override
            public Socket accept() throws IOException {
                return new Socket();
            }
        });
    }

    @Test(expected = Exception.class)
    public void getStore_inputNull_throwsException() {
        getStore(null);
    }

    @Test
    public void init_givenStoreCount2_thenStoreArrayIsSize2() throws Exception {
        runInit();
        Assert.assertNotNull(Master.storeSockets);
        Assert.assertEquals(2, Master.storeSockets.length);
    }

    @Test
    public void init_givenStoreCount2_thenInitializeHashStringWithSize2() throws Exception {
        runInit();
        Assert.assertNotNull(Master.hashString);
        Assert.assertEquals(2, Master.hashString.size);
    }

    @Test
    public void initStores_givenStoreCount2_thenInitializeStoresInArray() throws Exception {
        runInit();
        runInitStores();
        Assert.assertNotNull(Master.storeSockets);
        for (Socket store : Master.storeSockets) Assert.assertNotNull(store);
    }

    @Test
    public void getStore_inputIRequestWithKey123_returnsStore() throws SocketException {
        runInit();
        Assert.assertNotNull(Master.storeSockets);
        Master.storeSockets[0] = new Socket();
        Master.storeSockets[1] = new Socket();
        Master.storeSockets[0].setSendBufferSize(1);
        Master.storeSockets[1].setSendBufferSize(2);
        Socket actual = getStore(new ReadRequest(((char) 0) + ""));
        Assert.assertEquals(1, actual.getSendBufferSize());
    }


}
