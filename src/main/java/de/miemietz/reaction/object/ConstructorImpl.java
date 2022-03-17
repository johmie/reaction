package de.miemietz.reaction.object;

import java.lang.reflect.InvocationTargetException;

public class ConstructorImpl implements Constructor {

    @Override
    public Object construct(Class<?> anyClass) {

        java.lang.reflect.Constructor<?> constructor = null;
        try {
            constructor = anyClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            object = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return object;
    }
}
