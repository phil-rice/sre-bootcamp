package dev.srebootcamp.images.utils;

public interface FunctionWithException<From, To> {
    To apply(From from) throws Exception ;
}
