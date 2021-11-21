import java.util.function.Function;

public class GreetEvent implements Interceptor {
    @Override
    public String name() {
        return GreetEvent.class.getSimpleName();
    }

    @Override
    public Function<Context, Context> enter() {
        return context -> {
            String[] greet = context.getEvent();
            context.fx(FxName.print, greet[1]);
            return context;
        };
    }

    @Override
    public Function<Context, Context> exit() {
        return Function.identity();
    }
}
