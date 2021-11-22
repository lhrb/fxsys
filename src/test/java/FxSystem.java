import fx.EffectHandler;
import fx.GreetEventFxHandler;
import fx.Interceptor;
import org.junit.Test;

public class FxSystem {
    @Test
    public void test() {
        Interceptor greet = new GreetEventFxHandler();
        Interceptor effectHandler = new EffectHandler();
        //fx.Context ctx = new fx.Context(Arrays.asList(effectHandler, greet))
        //        .cofx(fx.CoFxId.EVENT, new String[]{"greet", "Peter"});

        //ctx.run();

    }
}
