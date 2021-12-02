package example.effects;

import example.DB;
import fx.Context;
import fx.data.Effect;
import fx.interceptors.EffectHandler;

import java.util.function.Function;

public class DbFxHandler implements EffectHandler {

    private final DB db;

    public DbFxHandler(DB db) {
        this.db = db;
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public Function<Context, Context> enter() {
        return ctx -> ctx.addCoFx(new DbCoFx(db));
    }

    @Override
    public Function<Context, Context> exit() {
        return ctx -> {
            Effect effect = ctx.getEffect(AddUserFx.id);
            if (effect instanceof AddUserFx addUserFx) {
                db.addUser(addUserFx.payload());
            }

            return ctx;
        };
    }
}
