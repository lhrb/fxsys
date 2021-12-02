package example;

import example.effects.DbFxHandler;
import example.events.AddUserEvent;
import example.events.AddUserEventHandler;
import fx.Context;
import fx.FxSys;
import fx.data.EventCoFx;
import fx.interceptors.Interceptor;

import java.util.Arrays;
import java.util.Collection;

public class Example {

    public static void main(String[] args) {
        var fxSystem = new FxSys();

        var db = new DB();
        var dbFxHandler = new DbFxHandler(db);
        Collection<Interceptor> interceptors = Arrays.asList(
                dbFxHandler,
                new AddUserEventHandler()
        );

        fxSystem.registerEventRoute(AddUserEvent.id, interceptors);

        fxSystem.dispatch(new AddUserEvent(new DB.User("Peter", 30)));
        fxSystem.dispatch(new AddUserEvent(new DB.User("Peter", 30)));
        fxSystem.dispatch(new AddUserEvent(new DB.User("Hilde", 32)));

        fxSystem.shutdown();
        db.getUsers().forEach(System.out::println);
    }
}
