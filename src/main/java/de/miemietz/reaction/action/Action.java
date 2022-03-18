package de.miemietz.reaction.action;

import java.lang.reflect.Method;

public record Action<T>(T proxy, Method method, Object[] args, Future<?> result) {
}
