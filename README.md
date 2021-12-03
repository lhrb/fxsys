# A library to manage events and effects

this is just a simple prototype.

### Inspiration
This project is inspired by [re-frame](https://day8.github.io/re-frame/) and [pedestal](http://pedestal.io/). It is 
totally worth it to go read their documentation first.

This project adopted `Interceptors` `Context` and the first three Dominoes `Event Dispatch`,
`Event Handling` `Effect Handling`

## About

### Interceptors
Interceptors are the building blocks for the data pipeline. Every Interceptor implements two Functions:

Enter: `Function<Context, Context>`

Exit: `Function<Context, Context>`

For every event we register a specific route of interceptors. A route gets then process like this:
<img src="https://cdn.rawgit.com/lhrb/fxsys/main/resources/interceptors.svg">


#### EffectHandler
Currently, EffectHandler are doing two things:

On enter we inject Coeffects to the context map. Coeffects are the current state of the world. Basically
data from the running application (for example the db) which does not come along with the event.

On exit we mutate the world by executing the effects attached to the context map. (Note current implementation 
does only allow one event of a type.)

#### EventHandler
Processing an event and compute a resulting effect which gets attached to the context map. 
Note the effect should get represented as data. This will make it easy to test the EventHandler.

## Design Goals

### Composablility
* Should be like Lego
* Interceptors and therefore the derived Event- and EffectHandler all share the same interface.
* Enter and Exit methods returning a function which takes a context map and returns a context map.
* Given the above we are able to configure routes in any way we can think of  .

### Testability
* **EventHandler** 
  * Takes data from a context map -> calculates a effect which is expressed as data and then 
appends the effect to the context map. Which makes EventHandler easily testable.
* **EffectHandler** 
  * Coeffect injection is easily testable (does it append the coeffect to the context map?).
  * Effect execution affects the world state and is therefore only testable by observing the world.
  * We can inject Interceptors into a route to remove other Interceptors from the queue/stack.
    
### Reuse
* For example a Database-Interceptor which injects on `enter` the DB as coeffect and performs all transaction 
  on `exit` should be reusable for different routes.

### Pragmatic
This is all fine, but I already have a lot of effectful legacy code working with events and I want to migrate this
quickly.

It's possible to just put effectful code into an Interceptors `enter` or `exit` method and ignore the event system.