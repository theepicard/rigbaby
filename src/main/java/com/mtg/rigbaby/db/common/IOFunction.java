package com.mtg.rigbaby.db.common;

import java.io.IOException;

@FunctionalInterface
public interface IOFunction<T, R> {
   R apply(T t) throws IOException;
}