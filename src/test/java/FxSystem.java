import org.junit.Test;

import java.util.Arrays;

public class FxSystem {
    @Test
    public void test() {
        Interceptor greet = new GreetEventFxHandler();
        Interceptor effectHandler = new EffectHandler();
        //Context ctx = new Context(Arrays.asList(effectHandler, greet))
        //        .cofx(CoFxId.EVENT, new String[]{"greet", "Peter"});

        //ctx.run();

    }
}
