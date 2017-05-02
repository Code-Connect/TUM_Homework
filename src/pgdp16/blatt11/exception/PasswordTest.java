package pgdp16.blatt11.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PasswordTest extends Password {
    private static final int RANGE = 10;
    private static int nrUpperShould;
    private static int nrLowerShould;
    private static int nrSpecialShould;
    private static int nrNumbersShould;
    private static int lengthShould;
    private final String pwd;
    private final Exception expected;

    public PasswordTest(String pwd, Exception expected, int nrUpperShould, int nrLowerShould, int nrSpecialShould,
                        int nrNumbersShould, int lengthShould, char[] illegalChars) {
        super(nrUpperShould, nrLowerShould, nrSpecialShould,
                nrNumbersShould, lengthShould, illegalChars);
        this.pwd = pwd;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        nrUpperShould = randomInt(RANGE);
        nrLowerShould = randomInt(RANGE);
        nrSpecialShould = randomInt(RANGE);
        nrNumbersShould = randomInt(RANGE);
        lengthShould = nrUpperShould + nrLowerShould + nrSpecialShould + nrNumbersShould;
        return Arrays.asList(new Object[][]{
                {"", null, 0, 0, 0, 0, 0, new char[]{}},
                {"", new NotEnoughUpper(1, 0), 1, 0, 0, 0, 0, new char[]{}},
                {"", new NotEnoughLower(1, 0), 0, 1, 0, 0, 0, new char[]{}},
                {"", new NotEnoughSpecial(1, 0), 0, 0, 1, 0, 0, new char[]{}},
                {"", new NotEnoughNumber(1, 0), 0, 0, 0, 1, 0, new char[]{}},
                {"", new NotLongEnoughExc(1, 0), 0, 0, 0, 0, 1, new char[]{}},
                {"\n", new IllegalCharExc('\n'), 0, 0, 0, 0, 0, new char[]{'\n'}},

                {"A", new NotEnoughUpper(2, 1), 2, 0, 0, 0, 0, new char[]{}},
                {"!", new NotEnoughSpecial(3, 1), 0, 0, 3, 0, 0, new char[]{}},
                {null, null, nrUpperShould, nrLowerShould, nrSpecialShould, nrNumbersShould, lengthShould, new char[]{}},
        });
    }

    private static int randomInt(int range) {
        return (char) (Math.random() * range);
    }

    private String buildPassword() {
        if (pwd != null) return pwd;
        return add(nrLowerShould, "l") + add(nrNumbersShould, "9")
                + add(nrSpecialShould, "*") + add(nrUpperShould, "U");
    }

    private String add(int times, String add) {
        String out = "";
        for (int i = times; i > 0; i--)
            out += add;
        return out;
    }

    @Test
    public void checkFormatTest() throws Exception {
        String actual = null + "";
        try {
            String pwd = buildPassword();
            System.out.println("PW: " + pwd);
            checkFormat(pwd);
        } catch (Exception e) {
            actual = e.toString();
        }
        Assert.assertEquals(expected + "", actual);
    }


}