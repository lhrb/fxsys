import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class FxSystem {
    @Test
    public void test() {
        Interceptor greet = new GreetEvent();
        Interceptor effectHandler = new EffectHandler();
        Context ctx = new Context(Arrays.asList(effectHandler, greet))
                .cofx(CofxName.event, new String[]{"greet", "Peter"});

        ctx.run();

    }
}
