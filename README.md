# A library to manage events and effects

this is just a simple prototype.

### Inspiration
This project is inspired by [re-frame](https://day8.github.io/re-frame/) and [pedestal](http://pedestal.io/). It is 
totally worth it to go read their documentation first.

This project adopted `Interceptors` `Context` and the first three Dominoes `Event Dispatch`,
`Event Handling` `Effect Handling`

## About

### Interceptors
This is the building block for the data pipeline. Every Interceptor implements two Functions:
Enter: `Function<Context, Context>`
Exit: `Function<Context, Context>`

For every event we register a specific route of interceptors. A route gets process like this:
<img src="https://cdn.rawgit.com/lhrb/fxsys/main/resources/interceptors.svg">


#### EffectHandler
