package fx;

import java.util.function.Function;

public class GreetEventFxHandler implements Interceptor {
    @Override
    public String name() {
        return GreetEventFxHandler.class.getSimpleName();
    }

    @Override
    public Function<Context, Context> enter() {
        return context -> {
            //context.fx();
            return context;
        };
    }

    @Override
    public Function<Context, Context> exit() {
        return Function.identity();
    }
}
