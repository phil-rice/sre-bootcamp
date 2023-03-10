package dev.srebootcamp.location.utils;

public interface FunctionWithException <From, To> {
    To apply(From from) throws Exception ;
}
