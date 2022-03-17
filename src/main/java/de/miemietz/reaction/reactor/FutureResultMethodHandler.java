package de.miemietz.reaction.reactor;

import de.miemietz.reaction.action.Future;
import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

public class FutureResultMethodHandler implements MethodHandler {

    private final Future<Object> result;

    public FutureResultMethodHandler(Future<Object> result) {

        this.result = result;
    }

    /**
     * Is called when a method is invoked on a proxy instance associated
     * with this handler.  This method must process that method invocation.
     *
     * @param self       the proxy instance.
     * @param thisMethod the overridden method declared in the super
     *                   class or interface.
     * @param proceed    the forwarder method for invoking the overridden
     *                   method.  It is null if the overridden method is
     *                   abstract or declared in the interface.
     * @param args       an array of objects containing the values of
     *                   the arguments passed in the method invocation
     *                   on the proxy instance.  If a parameter type is
     *                   a primitive type, the type of the array element
     *                   is a wrapper class.
     * @return the resulting value of the method invocation.
     * @throws Throwable if the method invocation fails.
     */
    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {

        Object returnValue = proceed.invoke(self, args);
        result.complete(returnValue);

        return result.get();
    }
}
