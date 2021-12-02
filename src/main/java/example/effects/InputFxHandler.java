package example.effects;

import example.DB;
import example.events.AddUserEvent;
import fx.Context;
import fx.data.Event;
import fx.interceptors.EffectHandler;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class InputFxHandler implements EffectHandler {

    private final Consumer<Event> eventConsumer;

    public InputFxHandler(Consumer<Event> eventConsumer) {
        this.eventConsumer = eventConsumer;
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public Function<Context, Context> enter() {
        return Function.identity();
    }

    @Override
    public Function<Context, Context> exit() {
        return ctx -> {
            var scanner = new Scanner(System.in);
            System.out.println("Enter username");
            String name = scanner.nextLine();
            System.out.println("Enter age");
            Integer age = scanner.nextInt();

            eventConsumer.accept(new AddUserEvent(new DB.User(name, age)));
            return ctx;
        };
    }
}
