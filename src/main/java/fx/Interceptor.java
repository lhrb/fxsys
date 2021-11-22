package fx;

import java.util.function.Function;

public interface Interceptor {
    String name();
    Function<Context, Context> enter();
    Function<Context, Context> exit();
}
