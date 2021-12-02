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
