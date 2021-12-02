package fx.interceptors;

import fx.Context;

import java.util.function.Function;

/**
 * Handles the event
 * should attach (if needed) the effect as data representation to the context map
 */
public interface EventHandler extends Interceptor {
    @Override
    default Function<Context, Context> exit() {
        return Function.identity();
    }
}
