package gad17.blatt04;

import junit.framework.Assert;
import org.junit.Test;

public class ClientTest {

    private IRequest makeRequest(String[] lineParts) {
        return Client.makeRequest(lineParts);
    }

    @Test(expected = RuntimeException.class)
    public void buildRequest_inputNull_throwsException() throws Exception {
        makeRequest(null);
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputEmptyArray_throwsException() throws Exception {
        makeRequest(new String[0]);
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputLenght1_throwsException() throws Exception {
        makeRequest(new String[1]);
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputReadLengthToBig_throwsException() throws Exception {
        makeRequest(new String[]{"read", "123", "1"});
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputStoreLengthToBig_throwsException() throws Exception {
        makeRequest(new String[]{"store", "123", "1", "Uebung"});
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputReadEmptyKey_throwsException() throws Exception {
        makeRequest(new String[]{"read", ""});
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputStoreEmptyKey_throwsException() throws Exception {
        makeRequest(new String[]{"store", "", "4"});
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputReadNoKey_throwsException() throws Exception {
        makeRequest(new String[]{"read"});
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputStoreNoKey_throwsException() throws Exception {
        makeRequest(new String[]{"store", "4"});
    }

    @Test(expected = Exception.class)
    public void buildRequest_inputStoreEmptyValue_throwsException() throws Exception {
        makeRequest(new String[]{"store", "123", ""});
    }

    @Test
    public void buildRequest_inputReadValid_returnsReadRequest() throws Exception {
        IRequest actual = makeRequest(new String[]{"read", "1"});
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof ReadRequest);
        Assert.assertEquals("1", actual.getKey());
    }

    @Test
    public void buildRequest_inputStoreValid_returnsStoreRequest() throws Exception {
        IRequest actual = makeRequest(new String[]{"store", "123", "1"});
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof StoreRequest);
        Assert.assertEquals("123", actual.getKey());
        Assert.assertEquals(1, ((StoreRequest) actual).getValue());
    }


}
