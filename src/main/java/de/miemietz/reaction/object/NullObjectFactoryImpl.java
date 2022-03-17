package de.miemietz.reaction.object;

import java.util.ArrayList;
import java.util.List;

public class NullObjectFactoryImpl implements NullObjectFactory {

    @Override
    public Object build(Class<?> anyClass) {

        if (anyClass == void.class) {

            return null;
        } else if (anyClass == int.class) {

            return 0;
        } else if (anyClass == long.class) {

            return 0L;
        } else if (anyClass == String.class) {

            return "";
        } else if (anyClass == List.class) {

            return new ArrayList<>();
        } else if (anyClass == boolean.class || anyClass == Boolean.class) {

            return Boolean.FALSE;
        } else if (anyClass == float.class) {

            return (float) 0;
        } else if (anyClass == double.class) {

            return (double) 0;
        } else if (anyClass == byte.class) {

            return Byte.valueOf("0");
        } else if (anyClass == short.class) {

            return Short.valueOf("0");
        }

        return new NullObject();
    }
}
