# Reaction

This project aims to run any existing Java code in parallel (without changing the implementation at all) to eliminate the blocking I/O and keep the overall execution time to a minimum.

## Status of this project

Reaction is still experimental and recursively parallelizes every method call of an object created with Reaction.

Asynchronous execution is thread-based and scales based on the number of CPU cores.

## Run your code with Reaction

Create an application instance of Reaction:
```
import de.miemietz.reaction.app.Application;
import de.miemietz.reaction.app.ThreadBootstrap;

Application reaction = ThreadBootstrap.app();
```
Now you can instantiate any class using Reaction's make method:
```
MyBlockingDemo myDemo = reaction.make(MyBlockingDemo.class);
```
Call any method of your class instance and Reaction will execute each line of code (non-blocking) in parallel:
```
myDemo.block(10); // Blocks for 10 seconds
myDemo.block(5);
MyBlockingDemo myDemo2 = myDemo.block(15);
myDemo2.block(10);
myDemo.block(5);
myDemo.block(15);
```
*In this example, the last line of code is passed after just a few milliseconds and all method calls are completed after 15 (instead of 60) seconds at the latest.*

Remember to run Reaction after all your method calls:
```
reaction.run();
```

## Missing (upcoming) features

- Support for objects injected via constructor, setter or created with the new keyword
- Support of coroutines (fibers)