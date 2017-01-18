package blatt11.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExceptionTest {
    public static final int RANGE = Character.MAX_VALUE;
    private final Exception test;
    private final Object[] params;

    public ExceptionTest(String name, Exception test, Object... params) {
        this.test = test;
        this.params = params;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        int is = randomInt(RANGE);
        int should = randomInt(RANGE) + 1 + is;
        return Arrays.asList(new Object[][]{
                {"IllegalCharExc", new IllegalCharExc((char) is), new Object[]{(char) is}},
                {"IllegalCharExc: \\n", new IllegalCharExc('\n'), new Object[]{"\\n"}},
                {"IllegalCharExc: \\t", new IllegalCharExc('\t'), new Object[]{"\\t"}},
                {"IllegalCharExc: \\r", new IllegalCharExc('\r'), new Object[]{"\\r"}},
                {"IllegalCharExc: \\b", new IllegalCharExc('\b'), new Object[]{"\\b"}},
                {"IllegalCharExc: \\f", new IllegalCharExc('\f'), new Object[]{"\\f"}},

                {"NotEnoughLower", new NotEnoughLower(should, is), new Object[]{should, is}},
                {"NotEnoughNumber", new NotEnoughNumber(should, is), new Object[]{should, is}},
                {"NotEnoughSpecial", new NotEnoughSpecial(should, is), new Object[]{should, is}},
                {"NotEnoughUpper", new NotEnoughUpper(should, is), new Object[]{should, is}},
                {"NotLongEnoughExc", new NotLongEnoughExc(should, is), new Object[]{should, is}},
        });
    }

    private static int randomInt(int range) {
        return (char) (Math.random() * range);
    }

    @Test
    public void toString_givenParams() throws Exception {
        try {
            throw test;
        } catch (Exception e) {
            System.out.println(e);
            Arrays.stream(params).forEach(param ->
                    Assert.assertTrue(e.toString().contains(
                            String.valueOf(param))));
        }
    }


}