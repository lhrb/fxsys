package example.events;

import example.effects.AddUserFx;
import example.effects.DbCoFx;
import fx.Context;
import fx.interceptors.EventHandler;

import java.util.function.Function;

public class AddUserEventHandler implements EventHandler {

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public Function<Context, Context> enter() {
        return ctx -> {
            DbCoFx db = ctx.getCoFx(DbCoFx.id);
            AddUserEvent event = ctx.getEvent();
            var user = event.payload();

            // simulate some logic (users could just be a set but that misses the point :P )
            if (!db.payload().getUsers().contains(user))
                ctx.addFx(new AddUserFx(event.payload()));

            return ctx;
        };
    }
}
