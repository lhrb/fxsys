package fx;

import java.util.function.Function;

public class EffectHandler implements Interceptor {

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Function<Context, Context> enter() {
        return Function.identity();
    }

    @Override
    public Function<Context, Context> exit() {
        return context -> {
            // process all effects
            String content = context.getEffect(new FxId("print"));
            System.out.println(content);

            return context;
        };
    }
}
