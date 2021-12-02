package example.events;

import example.effects.CreateUserInputFx;
import fx.Context;
import fx.interceptors.EventHandler;

import java.util.function.Function;

public class CreateUserEventHandler implements EventHandler {

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public Function<Context, Context> enter() {
        return ctx -> {
            // read out which fields are required or something
            ctx.addFx(new CreateUserInputFx());
            return ctx;
        };
    }
}
