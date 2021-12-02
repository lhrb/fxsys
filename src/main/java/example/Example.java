package example;

import example.effects.DbFxHandler;
import example.events.AddUserEvent;
import example.events.AddUserEventHandler;
import fx.Context;
import fx.data.EventCoFx;
import fx.interceptors.Interceptor;

import java.util.Arrays;
import java.util.Collection;

public class Example {

    public static void main(String[] args) {
        var db = new DB();
        var dbFxHandler = new DbFxHandler(db);
        Collection<Interceptor> interceptors = Arrays.asList(
                dbFxHandler,
                new AddUserEventHandler()
        );

        Context ctx = new Context(interceptors);
        ctx.addCoFx(new EventCoFx<>(new AddUserEvent(new DB.User("Peter", 30))));
        ctx.run();

        Context ctx2 = new Context(interceptors);
        ctx2.addCoFx(new EventCoFx<>(new AddUserEvent(new DB.User("Peter", 30))));
        ctx2.run();

        Context ctx3 = new Context(interceptors);
        ctx3.addCoFx(new EventCoFx<>(new AddUserEvent(new DB.User("Hilde", 30))));
        ctx3.run();

        db.getUsers().forEach(System.out::println);
    }
}
