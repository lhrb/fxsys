package fx.interceptors;

import fx.Context;

import java.util.function.Function;

public interface Interceptor {
    String name();
    Function<Context, Context> enter();
    Function<Context, Context> exit();
}
