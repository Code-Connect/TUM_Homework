package gad17.blatt04;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Map;

public class StoreTest {

    private Map getData() {
        return Store.data;
    }

    private ReadResponse onReadRequest(ReadRequest readRequest) {
        return Store.onReadRequest(readRequest);
    }

    private void onStoreRequest(StoreRequest storeRequest) {
        Store.onStoreRequest(storeRequest);
    }

    @Before
    public void setup() {
        Store.data = new Hashtable<>();
    }

    @Test
    public void table_givenMain_thenIsNotNull() throws Exception {
        Store.data = null;
        Store.init();
        Assert.assertNotNull(getData());
    }

    @Test(expected = Exception.class)
    public void eventStore_inputNull_throwsException() throws Exception {
        onStoreRequest(null);
    }

    @Test
    public void eventStore_input123_123_thenSaves123At123() {
        onStoreRequest(new StoreRequest("123", 123));
        Assert.assertNotNull(getData());
        Assert.assertNotNull(getData().get("123"));
        Assert.assertEquals(123, (int) getData().get("123"));
    }

    @Test(expected = Exception.class)
    public void eventRead_inputNull_throwsException() throws Exception {
        onReadRequest(null);
    }

    @Test
    public void eventRead_input123_valueNotFound_returnSerializableOptionalEmpty() {
        assertEventRead("123", new ReadResponse());
    }

    private void assertEventRead(String key, ReadResponse expected) {
        ReadResponse actual = onReadRequest(new ReadRequest(key));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getValue(), actual.getValue());
    }

    @Test
    public void eventRead_input123_valueFound_return123() {
        getData().put("123", 123);
        assertEventRead("123", new ReadResponse(123));
    }


}
